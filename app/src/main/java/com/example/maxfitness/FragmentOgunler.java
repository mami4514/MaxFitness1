package com.example.maxfitness;

import static android.content.ContentValues.TAG;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.fitness.databinding.FragmentOgunlerBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class FragmentOgunler extends Fragment {

    private FragmentOgunlerBinding binding;
    private FirebaseFirestore mFirestore=FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth;
    private String selectedMeal, selectedFood, selectedDate;
    private int kahvalti = 0, ogle = 0, aksam = 0;
    private SimpleDateFormat dateFormatter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentOgunlerBinding.inflate(getLayoutInflater(), container, false);

        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

        foods();
        kcalCalculator();

        return binding.getRoot();
    }

    public String food(){
        ArrayList<String> meal = new ArrayList<>();
        meal.add("Seçiniz");
        meal.add("Kahvaltı");
        meal.add("Öğle Yemeği");
        meal.add("Akşam Yemeği");
        ArrayAdapter<String> arrayMealAdapter;
        arrayMealAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, meal);
        binding.spinnerOgunSecimi.setAdapter(arrayMealAdapter);

        binding.spinnerOgunSecimi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedMeal = parent.getItemAtPosition(position).toString();
                if (selectedMeal.equals("Kahvaltı")){
                    ArrayList<String> food = new ArrayList<>();
                    food.add("Seçiniz");
                    food.add("YULAF EZMESİ: kcal, 404, 100 g");
                    food.add("HAŞLAMA TAVUK: kcal, 349, 100 g");
                    food.add("BASMATİ PİLAV:kcal, 155, 100 g");
                    food.add("FISTIK EZMESİ: kcal, 280, 100 g");
                    food.add("LABNE: kcal, 289, 100 g");
                    food.add("PROTEİN BAR: kcal, 69, 1 dilim");
                    food.add("TAM BUĞDAY EKMEK: kcal, 60, 1 dilim");
                    food.add("BEYAZ EKMEK: kcal, 53, 1 dilim");
                    ArrayAdapter<String> arrayFoodAdapter;
                    arrayFoodAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, food);
                    binding.spinnerYemekSecim.setAdapter(arrayFoodAdapter);
                } else if(selectedMeal.equals("Öğle Yemeği") || selectedMeal.equals("Akşam Yemeği")){
                    ArrayList<String> food = new ArrayList<>();
                    food.add("Seçiniz");
                    food.add("TAVUK: kcal, 94, 100 g");
                    food.add("PİLAV: kcal, 48, 100 g");
                    food.add("AVAKADO: kcal, 177, 100 g");
                    food.add("PROTEİN SHAKE: kcal, 53, 100 g");
                    food.add("HAŞLAMA KABAK: kcal, 174, 100 g");
                    food.add("HAŞLAMA PATATES: kcal, 186, 100 g");
                    food.add("HİNDİ FÜME: kcal, 83, 100 g");
                    food.add("BASMATİ PİLAV: kcal, 26, 100 g");
                    food.add("NOHUT: kcal, 240, 100 g");
                    food.add("YEŞİL MERCİMEK: kcal, 215, 100 g");
                    food.add("YEŞİL SALATA: kcal, 305, 100 g");
                    food.add("ZEYTİNYAĞLI SALATA, 132, 100 g");
                    food.add("YOĞURTLU MUZ: kcal, 185, 100 g");
                    food.add("İNCİR VE CEVİZ: kcal, 150, 100 g");
                    food.add("Kıymalı Makarna: kcal, 130, 100 g");
                    food.add("Karışık Pizza: kcal, 185, 100 g");
                    food.add("Zeytinyağlı Yaprak Sarma: kcal, 185, 100 g");
                    food.add("Peynirli Makarna: kcal, 140, 100 g");
                    food.add("Tavuklu Salata: kcal, 48, 100 g");
                    food.add("Kıymalı Dolma: kcal, 80, 100 g");
                    food.add("Zeytinyağlı Taze Fasulye: kcal, 50, 100 g");
                    food.add("Fırında Tavuk: kcal, 138, 100 g");
                    food.add("Karnıyarık: kcal, 134, 100 g");
                    food.add("Beyaz Peynir: kcal, 289, 100 g");
                    food.add("Beyaz Ekmek: kcal, 69, 1 dilim");
                    food.add("Çavdar Ekmeği: kcal, 60, 1 dilim");
                    food.add("Kepek Ekmeği: kcal, 53, 1 dilim");
                    food.add("Zeytinyağlı Dolma: kcal, 175, 100 g");
                    food.add("Çıkolatalı Pasta: kcal, 431, 1 dilim");
                    food.add("Bisküvi: kcal, 418, 100 gr");
                    food.add("Dondurma: kcal, 193, 3 top");
                    food.add("Kola: kcal, 59, 100 ml");
                    food.add("Meyveli Soda: kcal, 46, 100 ml");
                    food.add("Soğuk Çay: kcal, 30, 100 ml");
                    food.add("Şekersiz Çay: kcal, 3, 100 ml");
                    food.add("Şekersiz Sade Kahve: kcal, 9, 100 ml");
                    ArrayAdapter<String> arrayFoodAdapter;
                    arrayFoodAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, food);
                    binding.spinnerYemekSecim.setAdapter(arrayFoodAdapter);
                } else {
                    ArrayList<String> food = new ArrayList<>();
                    food.add("Lütfen İlk Önce Öğün Seçiniz");
                    ArrayAdapter<String> arrayFoodAdapter;
                    arrayFoodAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, food);
                    binding.spinnerYemekSecim.setAdapter(arrayFoodAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getActivity(), "Öğün Seçiniz", Toast.LENGTH_SHORT).show();
            }
        });
        return selectedMeal;
    }

    public void foods() {
        mAuth = FirebaseAuth.getInstance();

        binding.editTextTarih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar newCalendar = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar selectedCalendar = Calendar.getInstance();
                        selectedCalendar.set(year, monthOfYear, dayOfMonth);
                        selectedDate = dateFormatter.format(selectedCalendar.getTime());
                        binding.editTextTarih.setText(selectedDate);
                    }
                }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.show();
            }
        });

        binding.spinnerYemekSecim.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedFood = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getActivity(), "Yemek Seçilemedi", Toast.LENGTH_SHORT).show();
            }
        });

        selectedMeal = food();

        binding.buttonYemek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mFirestore.collection("Kullanıcılar").document(mAuth.getUid()).
                        get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot documentSnapshot = task.getResult();
                            List<Map<String, Object>> foodlist = (List<Map<String, Object>>) documentSnapshot.get("foodList");
                            ArrayList<Food> foodList1 = new ArrayList<>();
                            if (foodlist != null) {
                                for (Map<String, Object> foodData : foodlist) {
                                    Food food = new Food((String) foodData.get("Date"), (String) foodData.get("Meal"),(String) foodData.get("Food"));
                                    foodList1.add(food);
                                }
                            }
                            foodList1.add(new Food(selectedDate, selectedMeal, selectedFood));
                            mFirestore.collection("Kullanıcılar").document(mAuth.getUid()).
                                    update("foodList", FieldValue.arrayUnion(new Food(selectedDate, selectedMeal, selectedFood))).
                                    addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getActivity(), "Eklendi", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(getActivity(), "Döküman bulunamadı", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    public void kcalCalculator() {
        mAuth = FirebaseAuth.getInstance();
        String userId = mAuth.getUid();
        ArrayList<Integer> kahvalti_kcal = new ArrayList<>();
        ArrayList<Integer> ogle_kcal = new ArrayList<>();
        ArrayList<Integer> aksam_kcal = new ArrayList<>();

        binding.buttonCalHesabi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kahvalti=0;
                ogle=0;
                aksam=0;
                selectedDate = binding.editTextTarih.getText().toString();
                mFirestore.collection("Kullanıcılar").document(userId).
                        get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot documentSnapshot = task.getResult();
                                    List<Map<String, Object>> foodList = (List<Map<String, Object>>) documentSnapshot.get("foodList");

                                    if (foodList != null) {
                                        StringBuilder stringBuilder = new StringBuilder();

                                        for (Map<String, Object> food : foodList) {
                                            String date = (String) food.get("date");
                                            if (date.equals(selectedDate)){
                                                String mealName = (String) food.get("meal");
                                                if (mealName.equals("Kahvaltı")){
                                                    String foodName = (String) food.get("food");
                                                    stringBuilder.append(foodName);

                                                    String[] foodArray = foodName.split(", ");
                                                    String numericValue = foodArray[1];
                                                    int cal = Integer.parseInt(numericValue);
                                                    kahvalti_kcal.clear();
                                                    kahvalti_kcal.add(cal);
                                                    for (int i = 0; i < kahvalti_kcal.size(); i++) {
                                                        kahvalti += kahvalti_kcal.get(i);
                                                    }
                                                } else if (mealName.equals("Öğle Yemeği")){
                                                    String foodName = (String) food.get("food");
                                                    stringBuilder.append(foodName);

                                                    String[] foodArray = foodName.split(", ");
                                                    String numericValue = foodArray[1];
                                                    int cal = Integer.parseInt(numericValue);
                                                    ogle_kcal.clear();
                                                    ogle_kcal.add(cal);
                                                    for (int i = 0; i < ogle_kcal.size(); i++) {
                                                        ogle += ogle_kcal.get(i);
                                                    }
                                                } else if (mealName.equals("Akşam Yemeği")) {
                                                    String foodName = (String) food.get("food");
                                                    stringBuilder.append(foodName);

                                                    String[] foodArray = foodName.split(", ");
                                                    String numericValue = foodArray[1];
                                                    int cal = Integer.parseInt(numericValue);
                                                    aksam_kcal.clear();
                                                    aksam_kcal.add(cal);
                                                    for (int i = 0; i < aksam_kcal.size(); i++) {
                                                        aksam += aksam_kcal.get(i);
                                                    }
                                                }
                                            } else {
                                                binding.textViewCalBilgi.setText("Seçtiğiniz tarih için yemek bilgisi yok.");
                                            }
                                        }

                                        binding.textViewCalBilgi.setText(
                                                "Kahvaltıda yedikleriniz toplam "+kahvalti+" kaloridir.\n" +
                                                        "Öğle yemeğinde yedikleriniz toplam "+ogle+" kaloridir. \n"+
                                                        "Akşam yemeğinde yedikleriniz toplam "+aksam+" kaloridir.");
                                    } else {
                                        binding.textViewCalBilgi.setText("Hiç yemek eklenmemiş");
                                    }
                                } else {
                                    Log.d(TAG, "Firebase'den veriler getirilemedi.", task.getException());
                                }
                            }
                        });
            }
        });
    }

}