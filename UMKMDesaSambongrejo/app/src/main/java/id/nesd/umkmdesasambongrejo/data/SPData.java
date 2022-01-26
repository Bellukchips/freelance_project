package id.nesd.umkmdesasambongrejo.data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class SPData {

    @SuppressLint("StaticFieldLeak")
    private static SPData mInstance;
    @SuppressLint("StaticFieldLeak")
    private static Context mContext;

    private static final String spName = "umkm";
    private static final String kNama = "nama";
    private static final String kUsername = "username";
    private static final String kPassword = "password";

    private SPData(Context context) {
        mContext = context;
    }

    public static synchronized SPData getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SPData(context);
        }
        return mInstance;
    }

    public String getNama() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(spName, Context.MODE_PRIVATE);
        return sharedPreferences.getString(kNama, null);
    }

    public void setNama(String nama) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(spName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(kNama, nama);
        editor.apply();
    }

    public String getUsername() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(spName, Context.MODE_PRIVATE);
        return sharedPreferences.getString(kUsername, null);
    }

    public void setUsername(String username) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(spName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(kUsername, username);
        editor.apply();
    }

    public String getPassword() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(spName, Context.MODE_PRIVATE);
        return sharedPreferences.getString(kPassword, null);
    }

    public void setPassword(String password) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(spName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(kPassword, password);
        editor.apply();
    }

    public void clear(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(spName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}