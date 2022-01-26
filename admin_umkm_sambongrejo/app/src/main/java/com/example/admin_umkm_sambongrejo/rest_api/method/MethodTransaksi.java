package com.example.admin_umkm_sambongrejo.rest_api.method;

import com.example.admin_umkm_sambongrejo.rest_api.response.ResponseApi;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MethodTransaksi {
    @GET("data_transaksi/transaksi")
    Call<ResponseApi> getListTransaction();

    @FormUrlEncoded
    @POST("data_transaksi/transaksi")
    Call<ResponseApi> getTransactionByYear(
            @Field("year") String year
    );

}
