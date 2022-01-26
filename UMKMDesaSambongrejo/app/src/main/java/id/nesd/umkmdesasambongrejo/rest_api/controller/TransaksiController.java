package id.nesd.umkmdesasambongrejo.rest_api.controller;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;


import java.io.File;

import id.nesd.umkmdesasambongrejo.activity.HomeActivity;
import id.nesd.umkmdesasambongrejo.adapter.TransactionAdapter;
import id.nesd.umkmdesasambongrejo.rest_api.ConnectionService;
import id.nesd.umkmdesasambongrejo.rest_api.method.MethodTransaksi;
import id.nesd.umkmdesasambongrejo.rest_api.response.ResponseApi;
import id.nesd.umkmdesasambongrejo.tool.DialogLoading;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransaksiController {
    private static final String TAG = "TRANSAKSICONTROLLER";
    ConnectionService<MethodTransaksi> connectionService;

    public void getTransactionByIdUser(Context context, RecyclerView recyclerView,String id){
        connectionService = new  ConnectionService<>();
        connectionService.buildServices(MethodTransaksi.class).getTransactionById(id)
                .enqueue(new Callback<ResponseApi>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onResponse(Call<ResponseApi> call, Response<ResponseApi> response) {
                        assert response.body() != null;
                        Log.i(TAG,"Success "+response.body().getTransaksi().size());
                        TransactionAdapter reportAdapter = new TransactionAdapter(context,response.body().getTransaksi());
                        reportAdapter.notifyDataSetChanged();
                        recyclerView.setAdapter(reportAdapter);
                    }

                    @Override
                    public void onFailure(Call<ResponseApi> call, Throwable t) {
                        Log.i(TAG,t.getMessage());
                    }
                });
    }

    public void saveTransaction(Activity activity,Context context, String id_product, String id_konsumen, String total, String ongkir, String tujuan, String qty, String bukti_pembayaran){
        DialogLoading dialogLoading = new DialogLoading(activity);
        dialogLoading.startLoadingDialog();
        connectionService = new ConnectionService<>();
        /// request body
        File imagefile = new File(bukti_pembayaran);
        // Parsing any Media type file
        RequestBody imageToUpload = RequestBody.create(MediaType.parse("image/*"), imagefile);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("bukti_pembayaran", imagefile.getName(), imageToUpload);
        RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), imagefile.getName());
        RequestBody idProduct = RequestBody.create(MediaType.parse("text/plain"), id_product);
        RequestBody idKonsumen = RequestBody.create(MediaType.parse("text/plain"), id_konsumen);
        RequestBody totalP = RequestBody.create(MediaType.parse("text/plain"), total);
        RequestBody ongkirP = RequestBody.create(MediaType.parse("text/plain"), ongkir);
        RequestBody tujuanP = RequestBody.create(MediaType.parse("text/plain"), tujuan);
        RequestBody qtyP = RequestBody.create(MediaType.parse("text/plain"), qty);

        connectionService.buildServices(MethodTransaksi.class).saveTransaksi(idProduct,idKonsumen,totalP,ongkirP,tujuanP,qtyP,filename,fileToUpload)
                .enqueue(new Callback<ResponseApi>() {
                    @Override
                    public void onResponse(Call<ResponseApi> call, Response<ResponseApi> response) {
                        assert response.body() != null;
                        if(response.body().getStatus() == 1){
                            Toast.makeText(context,"Success transaction",Toast.LENGTH_LONG).show();
                            dialogLoading.dismissDialog();
                            Intent home = new Intent(context, HomeActivity.class);
                            context.startActivity(home);
                            activity.finish();
                        }else{
                            Toast.makeText(context,"Failed transaction",Toast.LENGTH_LONG).show();
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
