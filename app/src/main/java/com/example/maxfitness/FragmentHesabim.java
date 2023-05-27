package com.example.maxfitness;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.fitness.databinding.FragmentHesabimBinding;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class FragmentHesabim extends Fragment {

    private FragmentHesabimBinding binding;
    private FirebaseAuth mAuth;
    private String b, k, vki, xkilo;
    private float boy, kilo, bmi, kacKilo, maxBMI, minBMI, gerekliKilo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHesabimBinding.inflate(getLayoutInflater(), container, false);

        cinsiyet();
        bmiCalculator();

        logout();

        return binding.getRoot();
    }

    public void cinsiyet() {
        ArrayList<String> cinsiyet = new ArrayList<>();
        cinsiyet.add("Seçiniz");
        cinsiyet.add("Erkek");
        cinsiyet.add("Kadın");
        ArrayAdapter<String> arrayAdapter;
        arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, cinsiyet);
        binding.spinnerCinsiyet.setAdapter(arrayAdapter);
    }

    public void bmiCalculator(){
        binding.buttonBMIHesapla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b = binding.editTextBoyunuz.getText().toString();
                k = binding.editTextKilonuz.getText().toString();
                if (b.isEmpty() || k.isEmpty()){
                    Toast.makeText(getActivity(), "Alanlar boş olduğu için hesaplama yapılamaıyor", Toast.LENGTH_SHORT).show();
                } else {
                    bmi();
                }
            }
        });
    }

    public void bmi(){
        b = binding.editTextBoyunuz.getText().toString();
        k = binding.editTextKilonuz.getText().toString();
        boy = Float.parseFloat(b);
        kilo = Float.parseFloat(k);
        bmi = kilo/(boy*boy);
        vki = Float.toString(bmi);
        binding.textViewBMIBilgi.setText(vki);

        if (bmi<18.5){
            minBMI=(float) 18.5;
            kacKilo = minBMI*(boy*boy);
            gerekliKilo = kacKilo - kilo;
            xkilo = Float.toString(gerekliKilo);
            binding.textViewBMIBilgi.setText("Vücut Kitle İndeksiniz="+vki+" Aşırı zayıfsınız. En az "+xkilo+" kilo almanız gerekiyor.");
        } else if (bmi>=18.5 && bmi<24.9) {
            binding.textViewBMIBilgi.setText(" Kitle Beden İndeksiniz="+vki+" ideal kilodasınız. İdeal kilonuzu koruyunuz");
        } else if (bmi>=24.9 && bmi<29.9) {
            maxBMI = (float) 24.9;
            kacKilo = maxBMI*(boy*boy);
            gerekliKilo = kilo - kacKilo;
            xkilo = Float.toString(gerekliKilo);
            binding.textViewBMIBilgi.setText(" Kitle Beden İndeksiniz="+vki+"  Aşırı Kilolusunuz. En az "+xkilo+" kilo vermeniz gerekiyor.");
        } else if (bmi>=30) {
            maxBMI = (float) 24.9;
            kacKilo = maxBMI*(boy*boy);
            gerekliKilo = kilo - kacKilo;
            xkilo = Float.toString(gerekliKilo);
            binding.textViewBMIBilgi.setText("Vücut Kitle İndeksiniz="+vki+" Obezite teşhisi. En az"+xkilo+" kilo vermeniz gerekiyor. Bir uzman tarafından sizin için iyi olur.");
        }
    }



    public void logout() {
        mAuth = FirebaseAuth.getInstance();

        if (binding!=null){
            binding.buttonLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mAuth.signOut();
                    SharedPreferences preferences = getActivity().getSharedPreferences("session", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("isLoggedIn", false);
                    editor.apply();

                    SharedPreferences rememberMePreferences = getActivity().getSharedPreferences("checkbox", Context.MODE_PRIVATE);
                    SharedPreferences.Editor rememberMeEditor = rememberMePreferences.edit();
                    rememberMeEditor.putString("remember", "false");
                    rememberMeEditor.apply();

                    Toast.makeText(getActivity(), "Hesaptan çıkış yapıldı", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                }
            });
        }
    }
}