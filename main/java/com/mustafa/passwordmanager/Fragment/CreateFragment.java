package com.mustafa.passwordmanager.Fragment;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mustafa.passwordmanager.R;
import com.mustafa.passwordmanager.View.ShowActivity;
import com.mustafa.passwordmanager.databinding.FragmentCreateBinding;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateFragment extends Fragment {

    private FragmentCreateBinding binding;
    private SQLiteDatabase sqLiteDatabase;
    private String title,password;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCreateBinding.inflate(inflater,container,false);

        try {
            sqLiteDatabase = binding.getRoot().getContext().openOrCreateDatabase("PasswordManager", Context.MODE_PRIVATE,null);
        }catch (Exception e){
            e.printStackTrace();
        }


        binding.createPasswordBtn.setOnClickListener(item -> {

            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
            String str = formatter.format(date);

            title = binding.createTitleEditText.getText().toString();
            password = binding.createPasswordEditText.getText().toString().trim();

            if(!title.equals("") && !password.equals("")){
                if(password.length() < 8){
                    Toast.makeText(requireContext(), "Password must be at least 8 characters!", Toast.LENGTH_LONG).show();
                }else{
                    try {
                        sqLiteDatabase.execSQL("INSERT INTO Passwords(title,password,date) VALUES('"+title+"'," +
                                "'"+password+"','"+str+"');");

                        binding.createPasswordEditText.setText("");
                        binding.createTitleEditText.setText("");
                        Toast.makeText(requireContext(), "Successful", Toast.LENGTH_LONG).show();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }else {
                Toast.makeText(requireContext(), "Fill in the relevant fields!", Toast.LENGTH_LONG).show();
            }

        });

        return binding.getRoot();
    }
}