package com.example.admin_umkm_sambongrejo.rest_api.controller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.example.admin_umkm_sambongrejo.adapters.TransactionAdapter;
import com.example.admin_umkm_sambongrejo.models.TransaksiModel;
import com.example.admin_umkm_sambongrejo.rest_api.ConnectionService;
import com.example.admin_umkm_sambongrejo.rest_api.EndPoint;
import com.example.admin_umkm_sambongrejo.rest_api.method.MethodTransaksi;
import com.example.admin_umkm_sambongrejo.rest_api.response.ResponseApi;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransaksiController {
    private static final String TAG = "TRANSAKSICONTROLLER";
    ConnectionService<MethodTransaksi> connectionService;
    public void getAllTransaksi(Context context, RecyclerView recyclerView){
        connectionService = new  ConnectionService<>();
        connectionService.buildServices(MethodTransaksi.class).getListTransaction()
                .enqueue(new Callback<ResponseApi>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onResponse(Call<ResponseApi> call, Response<ResponseApi> response) {
                        assert response.body() != null;
                        Log.i(TAG,"Success "+response.body().getTransaksi().size());
                        TransactionAdapter transactionAdapter = new TransactionAdapter(context,response.body().getTransaksi());
                        transactionAdapter.notifyDataSetChanged();
                        recyclerView.setAdapter(transactionAdapter);
                    }

                    @Override
                    public void onFailure(Call<ResponseApi> call, Throwable t) {
                        Log.i(TAG,t.getMessage());
                    }
                });
    }
    public void getTransactionByYear(Context context, RecyclerView recyclerView,String year){
        connectionService = new  ConnectionService<>();
        connectionService.buildServices(MethodTransaksi.class).getTransactionByYear(year)
                .enqueue(new Callback<ResponseApi>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onResponse(Call<ResponseApi> call, Response<ResponseApi> response) {
                        assert response.body() != null;
                        Log.i(TAG,"Success "+response.body().getTransaksi().size());
                        TransactionAdapter transactionAdapter = new TransactionAdapter(context,response.body().getTransaksi());
                        transactionAdapter.notifyDataSetChanged();
                        recyclerView.setAdapter(transactionAdapter);
                    }

                    @Override
                    public void onFailure(Call<ResponseApi> call, Throwable t) {
                        Log.i(TAG,t.getMessage());
                    }
                });
    }
}
