package id.nesd.umkmdesasambongrejo.rest_api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import id.nesd.umkmdesasambongrejo.rest_api.EndPoint;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConnectionService<T> {
    private final OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
    private Gson gson = new GsonBuilder()
            .setLenient()
            .create();
    private final Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(EndPoint.URL).addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient.build());
    private final Retrofit retrofit =builder.build();
    public T buildServices(Class<T> serviceType){
        return retrofit.create(serviceType);
    }
}
