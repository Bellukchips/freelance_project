package id.nesd.umkmdesasambongrejo.rest_api.controller;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;



import java.io.File;

import id.nesd.umkmdesasambongrejo.adapter.ProductAdapter;
import id.nesd.umkmdesasambongrejo.rest_api.ConnectionService;
import id.nesd.umkmdesasambongrejo.rest_api.method.MethodProduct;
import id.nesd.umkmdesasambongrejo.rest_api.response.ResponseApi;
import id.nesd.umkmdesasambongrejo.tool.DialogLoading;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductController {
    private static final String TAG = "PRODUCTCONTROLLER";
    ConnectionService<MethodProduct> connectionService;
    public void getAllProduct(Context context, RecyclerView recyclerView){
        connectionService = new  ConnectionService<>();
        connectionService.buildServices(MethodProduct.class).getAllProduct()
                .enqueue(new Callback<ResponseApi>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onResponse(Call<ResponseApi> call, Response<ResponseApi> response) {
                        assert response.body() != null;
                        Log.i(TAG,"Success "+response.body().getProducts().size());
                        ProductAdapter productAdapter = new ProductAdapter(context,response.body().getProducts(),recyclerView);
                        productAdapter.notifyDataSetChanged();
                        recyclerView.setAdapter(productAdapter);
                    }

                    @Override
                    public void onFailure(Call<ResponseApi> call, Throwable t) {
                        Log.i(TAG,t.getMessage());
                    }
                });
    }


}
