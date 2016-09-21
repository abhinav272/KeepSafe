package com.abhinav.keepsafe;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.abhinav.keepsafe.Utils.KeepSafeKeys;
import com.abhinav.keepsafe.Utils.KeepSafePrefs;
import com.andrognito.pinlockview.IndicatorDots;
import com.andrognito.pinlockview.PinLockListener;
import com.andrognito.pinlockview.PinLockView;

/**
 * Created by indianrenters on 9/12/16.
 */
public class LoginActivity extends AppCompatActivity {

    private PinLockView mPinLockView;
    private IndicatorDots mIndicatorDots;
    private TextView tvBanner;
    private String pin1, pin2;
    private boolean flag;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setupLockView();
        if (flag = (getIntent().getBooleanExtra(KeepSafeKeys.IS_USER_PIN_AVAILABLE, false)))
            setupUI();
        else setupPinUI();
    }

    private void setupLockView() {
        mPinLockView = (PinLockView) findViewById(R.id.pin_lock_view);
        mIndicatorDots = (IndicatorDots) findViewById(R.id.indicator_dots);
        if (mIndicatorDots != null) {
            mIndicatorDots.setPinLength(6);
        }
        mPinLockView.attachIndicatorDots(mIndicatorDots);

        mPinLockView.setPinLockListener(new PinLockListener() {
            @Override
            public void onComplete(String pin) {
                Log.e("onComplete: ", pin);
                if(!flag){
                    if (pin1 == null) {
                        pin1 = pin;
                        tvBanner.setText(R.string.confirm_pin_string);
                        mPinLockView.resetPinLockView();
                    } else{
                        pin2 = pin;
                        if(pin1.equals(pin2)){
                            KeepSafePrefs.getInstance().setKeepSafeUserPin(LoginActivity.this, pin1);
                            Toast.makeText(LoginActivity.this, "PIN saved", Toast.LENGTH_SHORT).show();
                            navigateToHome();
                        } else {
                            Toast.makeText(LoginActivity.this, "PIN Mismatch", Toast.LENGTH_SHORT).show();
                            mPinLockView.resetPinLockView();
                            pin1 = pin2 = null;
                            tvBanner.setText(R.string.choose_pin_string);
                        }
                    }
                } else {
                    if(pin.equals(KeepSafePrefs.getInstance().getKeepSafeUserPin(LoginActivity.this)))
                        navigateToHome();
                    else {
                        mPinLockView.resetPinLockView();
                        Toast.makeText(LoginActivity.this, "Incorrect PIN", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onEmpty() {
                Log.e("onEmpty: ", "");
            }

            @Override
            public void onPinChange(int pinLength, String intermediatePin) {
                Log.e("onPinChange: ", intermediatePin + "  " + pinLength);
            }
        });
    }

    private void navigateToHome() {
        // add transition to navigate to Home Activity
        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        finish();
    }

    private void setupPinUI() {
        tvBanner = (TextView) findViewById(R.id.tv_banner);
        tvBanner.setVisibility(View.VISIBLE);
        tvBanner.setText(getString(R.string.choose_pin_string));
    }

    private void setupUI() {
        // left blank for future changes
    }
}
