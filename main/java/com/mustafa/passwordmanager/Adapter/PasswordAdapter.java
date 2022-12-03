package com.mustafa.passwordmanager.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.mustafa.passwordmanager.Model.GeneratePassword;
import com.mustafa.passwordmanager.R;
import com.mustafa.passwordmanager.databinding.RecyclerRowBinding;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class PasswordAdapter extends RecyclerView.Adapter<PasswordAdapter.ViewHolder> {

    ArrayList<GeneratePassword> generatePasswordArrayList;
    Context context;
    SQLiteDatabase sqLiteDatabase;

    public PasswordAdapter(ArrayList<GeneratePassword> generatePasswordArrayList, Context context) {
        this.generatePasswordArrayList = generatePasswordArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public PasswordAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerRowBinding recyclerRowBinding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(recyclerRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PasswordAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.binding.titleText.setText("Title: "+generatePasswordArrayList.get(position).title);
        holder.binding.passwordText.setText("Password: "+generatePasswordArrayList.get(position).password);
        holder.binding.dateText.setText("Date: "+generatePasswordArrayList.get(position).date);

        try {
            sqLiteDatabase = context.openOrCreateDatabase("PasswordManager",Context.MODE_PRIVATE,null);
        }catch (Exception e){
            e.printStackTrace();
        }

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context,v);
                popupMenu.getMenuInflater().inflate(R.menu.longclick_menu,popupMenu.getMenu());
                popupMenu.show();

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.delete:
                                sqLiteDatabase.execSQL("DELETE FROM Passwords WHERE title='"+generatePasswordArrayList.get(position).title+"' AND " +
                                        "password='"+generatePasswordArrayList.get(position).password+"' AND " +
                                        "date='"+generatePasswordArrayList.get(position).date+"'");
                                Toast.makeText(context, "Deleted Password!", Toast.LENGTH_LONG).show();
                                break;
                        }
                        return true;
                    }
                });
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return generatePasswordArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerRowBinding binding;
        public ViewHolder(RecyclerRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
