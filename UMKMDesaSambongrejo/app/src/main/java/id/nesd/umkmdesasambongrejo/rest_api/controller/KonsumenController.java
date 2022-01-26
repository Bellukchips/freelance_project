package id.nesd.umkmdesasambongrejo.rest_api.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;



import id.nesd.umkmdesasambongrejo.activity.HomeActivity;
import id.nesd.umkmdesasambongrejo.activity.LoginActivity;
import id.nesd.umkmdesasambongrejo.rest_api.ConnectionService;
import id.nesd.umkmdesasambongrejo.rest_api.method.MethodKonsumen;
import id.nesd.umkmdesasambongrejo.rest_api.response.ResponseApi;
import id.nesd.umkmdesasambongrejo.tool.DialogLoading;
import id.nesd.umkmdesasambongrejo.tool.PreferenceManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KonsumenController {
    private static final String TAG = "KONSUMENCONTROLLER";
    ConnectionService<MethodKonsumen> connectionService;
    public void loginUser(Activity activity, Context context, String email, String password){
        DialogLoading dialogLoading = new DialogLoading(activity);
        dialogLoading.startLoadingDialog();
        connectionService = new ConnectionService<>();
        connectionService.buildServices(MethodKonsumen.class).loginKonsumen(email,password)
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
                            preferenceManager.setPreference("name",response.body().getName());
                            preferenceManager.setPreference("address",response.body().getAddress());
                            preferenceManager.setPreference("gender",response.body().getGender());
                            preferenceManager.setPreference("email",response.body().getEmail());
                            preferenceManager.setPreference("id_user",response.body().getId_user());
                            preferenceManager.setPreference("is_google","0");
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
    public void saveKonsumen(Activity activity,Context context,String name,String email,String password,String gender){
        DialogLoading dialogLoading = new DialogLoading(activity);
        dialogLoading.startLoadingDialog();
        connectionService = new ConnectionService<>();
        connectionService.buildServices(MethodKonsumen.class).saveKonsumen(name, email, password, gender)
                .enqueue(new Callback<ResponseApi>() {
                    @Override
                    public void onResponse(Call<ResponseApi> call, Response<ResponseApi> response) {
                        assert response.body() != null;
                        if(response.body().getStatus() == 1){
                            Toast.makeText(context,"Success registered",Toast.LENGTH_LONG).show();
                            Intent login = new Intent(context, LoginActivity.class);
                            context.startActivity(login);
                            activity.finish();
                            dialogLoading.dismissDialog();

                        }else{
                            Toast.makeText(context,"Failed registered",Toast.LENGTH_LONG).show();
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
    public void saveKonsumenGoogle(Activity activity,Context context,String name,String email,String password,String gender){
        DialogLoading dialogLoading = new DialogLoading(activity);
        dialogLoading.startLoadingDialog();
        connectionService = new ConnectionService<>();
        connectionService.buildServices(MethodKonsumen.class).saveKonsumen(name, email, password, gender)
                .enqueue(new Callback<ResponseApi>() {
                    @Override
                    public void onResponse(Call<ResponseApi> call, Response<ResponseApi> response) {
                        assert response.body() != null;
                        if(response.body().getStatus() == 1){
                            Toast.makeText(context,"Success registered",Toast.LENGTH_LONG).show();
                            dialogLoading.dismissDialog();

                        }else{
                            Toast.makeText(context,"Failed registered",Toast.LENGTH_LONG).show();
                            dialogLoading.dismissDialog();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseApi> call, Throwable t) {
//                        Toast.makeText(context,t.getMessage(),Toast.LENGTH_LONG).show();
                        dialogLoading.dismissDialog();
                    }
                });
    }
    public void updateKonsumen(Activity activity,Context context,String id,String name,String email,String password,String address,String gender){
        DialogLoading dialogLoading = new DialogLoading(activity);
        dialogLoading.startLoadingDialog();
        connectionService = new ConnectionService<>();
        connectionService.buildServices(MethodKonsumen.class).updateKonsumen(id,name, email, password, gender, address)
                .enqueue(new Callback<ResponseApi>() {
                    @Override
                    public void onResponse(Call<ResponseApi> call, Response<ResponseApi> response) {
                        if(response.body().getStatus() == 1){
                            Toast.makeText(context,"Success update",Toast.LENGTH_LONG).show();
                            dialogLoading.dismissDialog();
                            Intent home = new Intent(context, HomeActivity.class);
                            context.startActivity(home);
                            activity.finish();
                        }else{
                            Toast.makeText(context,"Failed update",Toast.LENGTH_LONG).show();
                            dialogLoading.dismissDialog();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseApi> call, Throwable t) {
                        Toast.makeText(context,t.getMessage(),Toast.LENGTH_LONG).show();
                        dialogLoading.dismissDialog();
                    }});
    }
    public void getKonsumenByEmail(Context context,String email){
        connectionService = new ConnectionService<>();
        connectionService.buildServices(MethodKonsumen.class).getKonsumenByEmail(email)
                .enqueue(new Callback<ResponseApi>() {
                    @Override
                    public void onResponse(Call<ResponseApi> call, Response<ResponseApi> response) {
                        assert response.body() != null;
                       if(response.body().getStatus() == 1){
                           PreferenceManager preferenceManager = new PreferenceManager("LOGINPREFERENCE",context);
                           preferenceManager.setPreference("id_user",response.body().getId_user());
                           Log.i("IDUSER",response.body().getId_user());
                       }else{
                           Toast.makeText(context,"Failed get id",Toast.LENGTH_LONG).show();
                       }
                    }

                    @Override
                    public void onFailure(Call<ResponseApi> call, Throwable t) {
                        Toast.makeText(context,t.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
    }
}
