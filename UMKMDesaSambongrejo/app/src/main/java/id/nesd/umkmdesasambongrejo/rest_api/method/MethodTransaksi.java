package id.nesd.umkmdesasambongrejo.rest_api.method;
import id.nesd.umkmdesasambongrejo.rest_api.response.ResponseApi;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface MethodTransaksi {

    @GET("data_transaksi/transaksi/{id}")
    Call<ResponseApi> getTransactionById(
            @Path("id") String id
    );
    @Multipart
    @POST("data_transaksi/transaksi")
    Call<ResponseApi> saveTransaksi(
            @Part("id_product") RequestBody id_product,
            @Part("id_konsumen") RequestBody id_konsumen,
            @Part("total") RequestBody total,
            @Part("ongkir") RequestBody ongkir,
            @Part("tujuan") RequestBody tujuan,
            @Part("qty") RequestBody qty,
            @Part("bukti_pembayaran") RequestBody bukti_pembayaran,
            @Part MultipartBody.Part imageUpload
    );
}
