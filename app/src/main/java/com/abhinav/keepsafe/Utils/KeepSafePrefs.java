package com.abhinav.keepsafe.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by indianrenters on 9/12/16.
 */
public class KeepSafePrefs {

    private static KeepSafePrefs keepSafePrefs;
    private static Context mContext;
    private SharedPreferences sharedPreferences;
    private static final String KEEPSAFE_SHARED_PREFS = "keepsafe_shared_prefs";
    private static final String KEEPSAFE_USER_PIN = "keepsafe_user_pin";
    private static final String KEEPSAFE_SAFE_STRING = "safe_string";

    private KeepSafePrefs (){

    }

    private SharedPreferences getAppPreferences(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(KEEPSAFE_SHARED_PREFS, Context.MODE_PRIVATE);
        }
        return sharedPreferences;
    }

    public static KeepSafePrefs getInstance() {
        if (keepSafePrefs == null) {
            keepSafePrefs = new KeepSafePrefs();
        }
        return keepSafePrefs;
    }

    public void setKeepSafeUserPin(Context context, String pin) {
        getAppPreferences(context).edit().putString(KEEPSAFE_USER_PIN, pin).apply();
    }

    public String getKeepSafeUserPin(Context context){
        return getAppPreferences(context).getString(KEEPSAFE_USER_PIN,null);
    }

    public void setSafeItem(Context context, String s) {
        getAppPreferences(context).edit().putString(KEEPSAFE_SAFE_STRING, s).apply();
    }

    public String getSafeItem(Context context) {
        return getAppPreferences(context).getString(KEEPSAFE_SAFE_STRING, "");
    }
}
