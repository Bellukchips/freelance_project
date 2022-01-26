package com.example.admin_umkm_sambongrejo.utils;

import android.app.Activity;
import android.view.LayoutInflater;

import androidx.appcompat.app.AlertDialog;

import com.example.admin_umkm_sambongrejo.R;

public class DialogLoading {

    private Activity activity;
    private AlertDialog alertDialog;
    public DialogLoading(Activity activity){
        this.activity = activity;
    }

   public void startLoadingDialog()
    {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_loading,null));
        builder.setCancelable(false);
        alertDialog = builder.create();
        alertDialog.show();
    }


    public void dismissDialog()
    {
        alertDialog.dismiss();
    }
}
