package com.mustafa.passwordmanager.Fragment;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mustafa.passwordmanager.Adapter.PasswordAdapter;
import com.mustafa.passwordmanager.Model.GeneratePassword;
import com.mustafa.passwordmanager.databinding.FragmentPasswordsBinding;

import java.util.ArrayList;

public class PasswordsFragment extends Fragment {

    private FragmentPasswordsBinding binding;
    private SQLiteDatabase sqLiteDatabase;
    private PasswordAdapter adapter;
    private ArrayList<GeneratePassword> arrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPasswordsBinding.inflate(inflater,container,false);

        try {
            sqLiteDatabase = binding.getRoot().getContext().openOrCreateDatabase("PasswordManager", Context.MODE_PRIVATE,null);
        }catch (Exception e){
            e.printStackTrace();
        }

        arrayList = new ArrayList<>();
        binding.passwordRV.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext()));
        adapter = new PasswordAdapter(arrayList,binding.getRoot().getContext());
        binding.passwordRV.setAdapter(adapter);

        getData();
        return binding.getRoot();
    }

    private void getData(){
        try {

            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Passwords",null);
            for (cursor.moveToLast();!cursor.isBeforeFirst();cursor.moveToPrevious()){
                GeneratePassword generatePassword = new GeneratePassword(cursor.getString(1), cursor.getString(2), cursor.getString(3));
                arrayList.add(generatePassword);
            }
            adapter.notifyDataSetChanged();

            if(arrayList.isEmpty()){
                Toast.makeText(requireActivity(), "There is no password!", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}