package com.example.admin_umkm_sambongrejo.rest_api.controller;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.admin_umkm_sambongrejo.activity.HomeActivity;
import com.example.admin_umkm_sambongrejo.adapters.ProductAdapter;
import com.example.admin_umkm_sambongrejo.rest_api.ConnectionService;
import com.example.admin_umkm_sambongrejo.rest_api.method.MethodProduct;
import com.example.admin_umkm_sambongrejo.rest_api.response.ResponseApi;
import com.example.admin_umkm_sambongrejo.utils.DialogLoading;

import java.io.File;

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

    public void saveProduct(Activity activity, Context context, String name, String description, String price, String photo){
        DialogLoading dialogLoading = new DialogLoading(activity);
        dialogLoading.startLoadingDialog();
        connectionService = new ConnectionService<>();
        /// request body
        File imagefile = new File(photo);
        // Parsing any Media type file
        RequestBody imageToUpload = RequestBody.create(MediaType.parse("image/*"), imagefile);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("photo", imagefile.getName(), imageToUpload);
        RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), imagefile.getName());
        RequestBody nameProduct = RequestBody.create(MediaType.parse("text/plain"), name);
        RequestBody descProduct = RequestBody.create(MediaType.parse("text/plain"), description);
        RequestBody priceProduct = RequestBody.create(MediaType.parse("text/plain"), price);
        //
        connectionService.buildServices(MethodProduct.class).saveProduct(nameProduct,descProduct,priceProduct,filename,fileToUpload)
                .enqueue(new Callback<ResponseApi>() {
                    @Override
                    public void onResponse(Call<ResponseApi> call, Response<ResponseApi> response) {
                        assert response.body() != null;
                        if(response.body().getStatus() == 1){
                            Toast.makeText(context,"Success add new product",Toast.LENGTH_LONG).show();
                            dialogLoading.dismissDialog();
                            Intent home = new Intent(context,HomeActivity.class);
                            home.putExtra("isProduct",1);
                            context.startActivity(home);
                            activity.finish();
                        }else{
                            Toast.makeText(context,"Failed add new product",Toast.LENGTH_LONG).show();
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
    public void updateProductWithImage(Activity activity, Context context,String id, String name, String description, String price, String photo){
        DialogLoading dialogLoading = new DialogLoading(activity);
        dialogLoading.startLoadingDialog();
        connectionService = new ConnectionService<>();
        /// request body
        File imagefile = new File(photo);

        // Parsing any Media type file
        RequestBody imageToUpload = RequestBody.create(MediaType.parse("image/*"), imagefile);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("photo", imagefile.getName(), imageToUpload);
        RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), imagefile.getName());
        RequestBody idProduct = RequestBody.create(MediaType.parse("text/plain"), id);
        RequestBody nameProduct = RequestBody.create(MediaType.parse("text/plain"), name);
        RequestBody descProduct = RequestBody.create(MediaType.parse("text/plain"), description);
        RequestBody priceProduct = RequestBody.create(MediaType.parse("text/plain"), price);
        //
        connectionService.buildServices(MethodProduct.class).updateProductWithImage(idProduct,nameProduct,descProduct,priceProduct,filename,fileToUpload)
                .enqueue(new Callback<ResponseApi>() {
                    @Override
                    public void onResponse(Call<ResponseApi> call, Response<ResponseApi> response) {
                        assert response.body() != null;
                        if(response.body().getStatus() == 1){
                            Toast.makeText(context,"Success update product",Toast.LENGTH_LONG).show();
                            dialogLoading.dismissDialog();
                            Intent home = new Intent(context,HomeActivity.class);
                            context.startActivity(home);
                            activity.finish();
                        }else{
                            Toast.makeText(context,"Failed add new product",Toast.LENGTH_LONG).show();
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
    public void updateProduct(Activity activity, Context context,String id, String name, String description, String price){
        DialogLoading dialogLoading = new DialogLoading(activity);
        dialogLoading.startLoadingDialog();
        connectionService = new ConnectionService<>();
        // Parsing any Media type file
        RequestBody idProduct = RequestBody.create(MediaType.parse("text/plain"), id);
        RequestBody nameProduct = RequestBody.create(MediaType.parse("text/plain"), name);
        RequestBody descProduct = RequestBody.create(MediaType.parse("text/plain"), description);
        RequestBody priceProduct = RequestBody.create(MediaType.parse("text/plain"), price);
        //
        connectionService.buildServices(MethodProduct.class).updateProduct(idProduct,nameProduct,descProduct,priceProduct)
                .enqueue(new Callback<ResponseApi>() {
                    @Override
                    public void onResponse(Call<ResponseApi> call, Response<ResponseApi> response) {
                        assert response.body() != null;
                        if(response.body().getStatus() == 1){
                            Toast.makeText(context,"Success update product",Toast.LENGTH_LONG).show();
                            dialogLoading.dismissDialog();
                            Intent home = new Intent(context,HomeActivity.class);
                            context.startActivity(home);
                            activity.finish();
                        }else{
                            Toast.makeText(context,"Failed update product",Toast.LENGTH_LONG).show();
                            dialogLoading.dismissDialog();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseApi> call, Throwable t) {
                        Toast.makeText(context,t.getMessage(),Toast.LENGTH_LONG).show();
                        Log.i(TAG,t.getMessage());
                        dialogLoading.dismissDialog();

                    }
                });
    }
    public void deleteProduct(String id,Context context){
        connectionService = new  ConnectionService<>();
        connectionService.buildServices(MethodProduct.class).deleteProduct(id)
                .enqueue(new Callback<ResponseApi>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onResponse(Call<ResponseApi> call, Response<ResponseApi> response) {
                        assert response.body() != null;
                        if(response.body().getStatus() == 1){
                            Toast.makeText(context,"Success delete product",Toast.LENGTH_LONG).show();

                        }else{
                            Toast.makeText(context,"Failed delete product",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseApi> call, Throwable t) {
                        Log.i(TAG,t.getMessage());
                    }
                });
    }
}
