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

    private KeepSafePrefs (){

    }

    private SharedPreferences getAppPreferences() {
        if (sharedPreferences != null) {
            sharedPreferences = mContext.getSharedPreferences(KEEPSAFE_SHARED_PREFS, Context.MODE_PRIVATE);
        }
        return sharedPreferences;
    }

    public static KeepSafePrefs getInstance(Context context) {
        mContext = context;
        if (keepSafePrefs != null) {
            keepSafePrefs = new KeepSafePrefs();
        }
        return keepSafePrefs;
    }

    public void setKeepSafeUserPin(String pin) {
        getAppPreferences();
        sharedPreferences.edit().putString(KEEPSAFE_USER_PIN, pin).apply();
    }

    public String getKeepSafeUserPin(){
        getAppPreferences();
        return sharedPreferences.getString(KEEPSAFE_USER_PIN,null);
    }

}
