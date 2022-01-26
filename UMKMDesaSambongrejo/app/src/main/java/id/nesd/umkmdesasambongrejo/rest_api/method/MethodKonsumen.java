package id.nesd.umkmdesasambongrejo.rest_api.method;


import id.nesd.umkmdesasambongrejo.rest_api.response.ResponseApi;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MethodKonsumen {
    @FormUrlEncoded
    @POST("data_konsumen/konsumen")
    Call<ResponseApi> saveKonsumen(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("gender") String gender

    );
    @FormUrlEncoded
    @POST("data_konsumen/konsumen")
    Call<ResponseApi> loginKonsumen(
            @Field("email") String email,
            @Field("password") String password
    );
    @FormUrlEncoded
    @POST("data_konsumen/konsumen/{id}")
    Call<ResponseApi> updateKonsumen(
            @Path("id") String id,
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("gender") String gender,
            @Field("address") String address
    );

    @GET("data_konsumen/konsumen")
    Call<ResponseApi> getKonsumenByEmail(
            @Query("email") String email
    );
}
