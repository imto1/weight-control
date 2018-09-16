package ir.behmerd.weightcontrol.common;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Shared preferences class.
 */
public class Preferences {
    Context context;
    private static SharedPreferences preferences;
    final String PREFERENCE = "BehmerdWeightControl";
    final String LANGUAGE = "AppLocale";
    public Preferences(Context context){
        this.context = context;
    }

    public boolean setLanguage(String language){
        preferences = context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(LANGUAGE, language);
        return editor.commit();
    }

    public String getLanguage(){
        preferences = context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        return preferences.getString(LANGUAGE, "system");
    }
}
