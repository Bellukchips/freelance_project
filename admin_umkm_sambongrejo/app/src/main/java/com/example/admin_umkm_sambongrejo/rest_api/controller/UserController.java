package com.example.admin_umkm_sambongrejo.rest_api.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.admin_umkm_sambongrejo.activity.HomeActivity;
import com.example.admin_umkm_sambongrejo.rest_api.ConnectionService;
import com.example.admin_umkm_sambongrejo.rest_api.method.MethodUsers;
import com.example.admin_umkm_sambongrejo.rest_api.response.ResponseApi;
import com.example.admin_umkm_sambongrejo.utils.DialogLoading;
import com.example.admin_umkm_sambongrejo.utils.PreferenceManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserController {
    private static final String TAG = "USERCONTROLLER";
    private ConnectionService<MethodUsers> connectionService;
    public void loginUser(Activity activity, Context context, String username, String password){
        DialogLoading dialogLoading = new DialogLoading(activity);
        dialogLoading.startLoadingDialog();
        connectionService = new ConnectionService<>();
        connectionService.buildServices(MethodUsers.class).loginUser(username,password)
                .enqueue(new Callback<ResponseApi>() {
                    @Override
                    public void onResponse(Call<ResponseApi> call, Response<ResponseApi> response) {
                        assert response.body() != null;
                        if(response.body().getStatus() == 1){
                            Intent home = new Intent(context, HomeActivity.class);
                            context.startActivity(home);
                            activity.finish();
                            Toast.makeText(context,"Login Success",Toast.LENGTH_LONG).show();
                            //set locale storage
                            PreferenceManager preferenceManager = new PreferenceManager("LOGINPREFERENCE",context);
                            preferenceManager.setPreference("login","1");
                            //dismiss dialog loading
                            dialogLoading.dismissDialog();
                        }else{
                            Toast.makeText(context,"Login Failed",Toast.LENGTH_LONG).show();
                            dialogLoading.dismissDialog();
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseApi> call, Throwable t) {
                        Log.e(TAG,t.getMessage());
                    }
                });
    }
}
