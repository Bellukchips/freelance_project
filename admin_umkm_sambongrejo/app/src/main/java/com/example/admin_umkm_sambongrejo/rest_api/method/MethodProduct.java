package com.example.admin_umkm_sambongrejo.rest_api.method;

import com.example.admin_umkm_sambongrejo.rest_api.response.ResponseApi;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface MethodProduct {
    //product
    @GET("data_product/product")
    Call<ResponseApi> getAllProduct();

    @Multipart
    @POST("data_product/product")
    Call<ResponseApi> saveProduct(
            @Part("name") RequestBody name,
            @Part("description") RequestBody description,
            @Part("price") RequestBody price,
            @Part("photo") RequestBody photo,
            @Part MultipartBody.Part imageUpload
    );

    @Multipart
    @POST("data_product/product")
    Call<ResponseApi> updateProductWithImage(
            @Part("id") RequestBody id,
            @Part("name") RequestBody name,
            @Part("description") RequestBody description,
            @Part("price") RequestBody price,
            @Part("photo") RequestBody photo,
            @Part MultipartBody.Part imageUpload
    );
    @Multipart
    @POST("data_product/product")
    Call<ResponseApi> updateProduct(
            @Part("id") RequestBody id,
            @Part("name") RequestBody name,
            @Part("description") RequestBody description,
            @Part("price") RequestBody price
    );
    @DELETE("data_product/product/{id}")
    Call<ResponseApi>deleteProduct(
            @Path("id") String id
    );
}
