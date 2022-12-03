package com.mustafa.passwordmanager.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.mustafa.passwordmanager.databinding.ActivityShowBinding;

import java.util.Objects;

public class ShowActivity extends AppCompatActivity {

    ActivityShowBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.successfulText.setVisibility(View.INVISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                binding.firstSwitch.setChecked(true);
            }
        },3000);


            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    binding.secondSwitch.setChecked(true);
                    binding.successfulText.setVisibility(View.VISIBLE);
                }
            },6000);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(ShowActivity.this,MainActivity.class));
                finish();
                Toast.makeText(ShowActivity.this, "Generated password.", Toast.LENGTH_SHORT).show();
            }
        },8000);
    }
}