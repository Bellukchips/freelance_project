package id.nesd.umkmdesasambongrejo.tool;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {
    private Context context;
    private  SharedPreferences.Editor editorpreferences;
    private  SharedPreferences preferences;
    private String namePref;

    public PreferenceManager(String namePref,Context context){
        this.context = context;
        preferences = this.context.getSharedPreferences(namePref, MODE_PRIVATE);
        editorpreferences = preferences.edit();
    }
    public void setPreference(String key, String value){
        editorpreferences.putString(key,value);
        editorpreferences.apply();
    }
    public String getPreference(String key) {

        return preferences.getString(key, "");
    }

    public void deletePreference(){
        preferences.edit().remove(this.namePref).apply();
    }
}
