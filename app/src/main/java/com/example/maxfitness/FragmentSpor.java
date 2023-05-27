package com.example.maxfitness;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.fitness.databinding.FragmentSporBinding;

public class FragmentSpor extends Fragment {

    private FragmentSporBinding binding;
    private String s, m, b, kal;
    private int sinav, mekik, barfiks, yakilanKalori;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSporBinding.inflate(getLayoutInflater(), container, false);

        addButton();

        return binding.getRoot();
    }

    public void addButton(){
        binding.buttonEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s = binding.editTextSinav.getText().toString();
                m = binding.editTextMekik.getText().toString();
                b = binding.editTextBarfiks.getText().toString();

                if (s.isEmpty() || m.isEmpty() || b.isEmpty()){
                    Toast.makeText(getActivity(), "Alanlar boş olduğu için hesaplama yapılamaıyor", Toast.LENGTH_SHORT).show();
                } else {
                    calCalculator();
                }
            }
        });
    }

    public void calCalculator(){
        s = binding.editTextSinav.getText().toString();
        m = binding.editTextMekik.getText().toString();
        b = binding.editTextBarfiks.getText().toString();

        sinav = Integer.parseInt(s);
        mekik = Integer.parseInt(m);
        barfiks = Integer.parseInt(b);

        yakilanKalori = (sinav*2)+(mekik*2)+(barfiks*5);
        kal = Integer.toString(yakilanKalori);
        binding.textViewHareketBilgi.setText(" Bugün "+s+" set dumbell row "+m+" dakika cardio "+b+" set dumbell fly yapmissiniz. Bu yaptıklarınız sayesinde ortalama "+kal+" kalori yaktınız.");
    }
}