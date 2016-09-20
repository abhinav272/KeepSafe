package com.abhinav.keepsafe;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.abhinav.keepsafe.Utils.KeepSafeKeys;
import com.abhinav.keepsafe.Utils.KeepSafePrefs;

import java.util.HashSet;

public class SplashActivity extends AppCompatActivity {

    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                intent = new Intent(SplashActivity.this, LoginActivity.class);
                if(KeepSafePrefs.getInstance().getKeepSafeUserPin(SplashActivity.this)!=null)
                    intent.putExtra(KeepSafeKeys.IS_USER_PIN_AVAILABLE, true);
                else intent.putExtra(KeepSafeKeys.IS_USER_PIN_AVAILABLE, false);

                startActivity(intent);
                finish();
            }
        },2000);

    }
}
