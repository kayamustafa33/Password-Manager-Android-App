package com.mustafa.passwordmanager.Model;

public class GeneratePassword {
    public String password;
    public String title;
    public String date;

    public GeneratePassword(String title, String password, String date) {
        this.password = password;
        this.title = title;
        this.date = date;
    }
}
