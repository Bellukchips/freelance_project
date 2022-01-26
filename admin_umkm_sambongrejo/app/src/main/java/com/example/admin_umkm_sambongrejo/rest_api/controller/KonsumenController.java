package com.example.admin_umkm_sambongrejo.rest_api.controller;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.admin_umkm_sambongrejo.activity.HomeActivity;
import com.example.admin_umkm_sambongrejo.adapters.KonsumenAdapter;
import com.example.admin_umkm_sambongrejo.rest_api.ConnectionService;
import com.example.admin_umkm_sambongrejo.rest_api.method.MethodKonsumen;
import com.example.admin_umkm_sambongrejo.rest_api.response.ResponseApi;
import com.example.admin_umkm_sambongrejo.utils.DialogLoading;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KonsumenController {
    private static final String TAG = "KONSUMENCONTROLLER";
    ConnectionService<MethodKonsumen> connectionService;
    public void getAllKonsumen(Context context, RecyclerView recyclerView){
        connectionService = new  ConnectionService<>();
        connectionService.buildServices(MethodKonsumen.class).getListKonsumen()
                .enqueue(new Callback<ResponseApi>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onResponse(Call<ResponseApi> call, Response<ResponseApi> response) {
                        assert response.body() != null;
                        Log.i(TAG,"Success "+response.body().getKonsumen().size());
                        KonsumenAdapter konsumenAdapter = new KonsumenAdapter(context,response.body().getKonsumen(),recyclerView);
                        konsumenAdapter.notifyDataSetChanged();
                        recyclerView.setAdapter(konsumenAdapter);
                    }

                    @Override
                    public void onFailure(Call<ResponseApi> call, Throwable t) {
                        Log.i(TAG,t.getMessage());
                    }
                });
    }
    public void deleteKonsumen(String id,Context context){
        connectionService = new  ConnectionService<>();
        connectionService.buildServices(MethodKonsumen.class).deleteKonsumen(id)
                .enqueue(new Callback<ResponseApi>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onResponse(Call<ResponseApi> call, Response<ResponseApi> response) {
                        assert response.body() != null;
                        if(response.body().getStatus() == 1){
                            Toast.makeText(context,"Success delete data",Toast.LENGTH_LONG).show();

                        }else{
                            Toast.makeText(context,"Failed delete data",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseApi> call, Throwable t) {
                        Log.i(TAG,t.getMessage());
                    }
                });
    }

    public void saveKonsumen(Activity activity,Context context,String name,String username,String password,String address,String gender){
        DialogLoading dialogLoading = new DialogLoading(activity);
        dialogLoading.startLoadingDialog();
        connectionService = new ConnectionService<>();
        connectionService.buildServices(MethodKonsumen.class).saveKonsumen(name, username, password, gender, address)
                .enqueue(new Callback<ResponseApi>() {
                    @Override
                    public void onResponse(Call<ResponseApi> call, Response<ResponseApi> response) {
                        if(response.body().getStatus() == 1){
                            Toast.makeText(context,"Success add new konsumen",Toast.LENGTH_LONG).show();
                            dialogLoading.dismissDialog();
                            Intent home = new Intent(context, HomeActivity.class);
                            home.putExtra("isProduct",0);
                            context.startActivity(home);
                            activity.finish();
                        }else{
                            Toast.makeText(context,"Failed add new konsumen",Toast.LENGTH_LONG).show();
                            dialogLoading.dismissDialog();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseApi> call, Throwable t) {
                        Toast.makeText(context,t.getMessage(),Toast.LENGTH_LONG).show();
                        dialogLoading.dismissDialog();
                    }
                });
    }
    public void updateKonsumen(Activity activity,Context context,String id,String name,String username,String password,String address,String gender){
        DialogLoading dialogLoading = new DialogLoading(activity);
        dialogLoading.startLoadingDialog();
        connectionService = new ConnectionService<>();
        connectionService.buildServices(MethodKonsumen.class).updateKonsumen(id,name, username, password, gender, address)
                .enqueue(new Callback<ResponseApi>() {
                    @Override
                    public void onResponse(Call<ResponseApi> call, Response<ResponseApi> response) {
                        if(response.body().getStatus() == 1){
                            Toast.makeText(context,"Success update konsumen",Toast.LENGTH_LONG).show();
                            dialogLoading.dismissDialog();
                            Intent home = new Intent(context, HomeActivity.class);
                            home.putExtra("isProduct",0);
                            context.startActivity(home);
                            activity.finish();
                        }else{
                            Toast.makeText(context,"Failed update konsumen",Toast.LENGTH_LONG).show();
                            dialogLoading.dismissDialog();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseApi> call, Throwable t) {
                        Toast.makeText(context,t.getMessage(),Toast.LENGTH_LONG).show();
                        dialogLoading.dismissDialog();
                    }
                });
    }
}
