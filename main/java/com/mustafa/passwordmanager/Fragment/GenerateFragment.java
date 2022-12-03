package com.mustafa.passwordmanager.Fragment;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.mustafa.passwordmanager.Adapter.PasswordAdapter;
import com.mustafa.passwordmanager.Model.GeneratePassword;
import com.mustafa.passwordmanager.R;
import com.mustafa.passwordmanager.View.ShowActivity;
import com.mustafa.passwordmanager.databinding.FragmentGenerateBinding;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GenerateFragment extends Fragment {

    private FragmentGenerateBinding binding;
    private SQLiteDatabase sqLiteDatabase;
    private String title;
    int count = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentGenerateBinding.inflate(inflater,container,false);

        try {
            sqLiteDatabase = binding.getRoot().getContext().openOrCreateDatabase("PasswordManager",Context.MODE_PRIVATE,null);
        }catch (Exception e){
            e.printStackTrace();
        }

        binding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                binding.seekBarText.setText(progress+"/15 Characters");
                count = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        binding.generateBtn.setOnClickListener(item -> {
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
            String str = formatter.format(date);
            title = binding.titleEditText.getText().toString();


            if(!title.equals("")){
                if(count < 8){
                    Toast.makeText(requireContext(), "Password must be at least 8 characters!", Toast.LENGTH_SHORT).show();
                }else{
                    String generatePassword = GeneratePassword();
                    try {
                        sqLiteDatabase.execSQL("INSERT INTO Passwords(title,password,date) VALUES('"+title+"'," +
                                "'"+generatePassword+"','"+str+"');");

                        startActivity(new Intent(getActivity(),ShowActivity.class));

                        binding.titleEditText.setText("");
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

            }else{
                Toast.makeText(requireContext(), "Enter the title!", Toast.LENGTH_LONG).show();
            }
        });

        return binding.getRoot();
    }

    public String GeneratePassword(){
        String lower_cases = "qwertyuopasdfghjklizxcvbnm";
        String upper_cases = "QWERTYUIOPASDFGHJKLZXCVBNM";
        String special_character = "~`!@#$%^&*()-_=+[{]}\\|;:,'<.>/?";
        String numeric_character = "1234567890";
        String password = "";
        for(int i = 0; i < count ; i++){
            int rand = (int) (4 * Math.random());

            switch (rand){
                case 0:
                    rand = (int) ((lower_cases.length()) * Math.random());
                    password += String.valueOf(lower_cases.charAt(rand));
                    break;
                case 1:
                    rand = (int) ((upper_cases.length()) * Math.random());
                    password += String.valueOf(upper_cases.charAt(rand));
                    break;
                case 2:
                    rand = (int) ((special_character.length()) * Math.random());
                    password += String.valueOf(special_character.charAt(rand));
                    break;
                case 3:
                    rand = (int) ((numeric_character.length()) * Math.random());
                    password += String.valueOf(numeric_character.charAt(rand));
                    break;
            }
        }
        return password;
    }

}