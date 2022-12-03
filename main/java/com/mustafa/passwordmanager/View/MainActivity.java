package com.mustafa.passwordmanager.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.mustafa.passwordmanager.Fragment.GeneratePasswordFragment;
import com.mustafa.passwordmanager.Fragment.PasswordsFragment;
import com.mustafa.passwordmanager.R;
import com.mustafa.passwordmanager.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        try {
            sqLiteDatabase = this.openOrCreateDatabase("PasswordManager",MODE_PRIVATE,null);
            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS Passwords(id INTEGER PRIMARY KEY," +
                    "title VARCHAR," +
                    "password VARCHAR," +
                    "date VARCHAR);");
        }catch (Exception e){
            e.printStackTrace();
        }


        replaceFragment(new PasswordsFragment());
        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            
            switch (item.getItemId()){
                case R.id.passwords:
                    replaceFragment(new PasswordsFragment());
                    break;
                case R.id.generatePassword:
                    replaceFragment(new GeneratePasswordFragment());
                    break;
            }
            
            return true;
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();
    }
}