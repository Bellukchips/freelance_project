package com.example.admin_umkm_sambongrejo.utils;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class Helper {
    public void hideAppBar(AppCompatActivity activity) {
        Objects.requireNonNull(activity.getSupportActionBar()).hide();
    }

    public void setTitleAppBar(AppCompatActivity activity, String title) {
        Objects.requireNonNull(activity.getSupportActionBar()).setTitle(title);
    }

    public String getServType(int i) {
        if (i == 0) {
            return "Ice";
        } else if (i == 1) {
            return "Hot";
        } else {
            return "Ice/Hot";
        }
    }
}
