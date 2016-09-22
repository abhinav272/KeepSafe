package com.abhinav.keepsafe.services;

import android.accessibilityservice.AccessibilityService;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

/**
 * Created by abhinav.sharma on 9/21/2016.
 */
public class KeepSafeService extends AccessibilityService {
    private static final String TAG = KeepSafeService.class.getSimpleName();

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        Log.e(TAG, "onServiceConnected: ");
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        Log.e(TAG, "onAccessibilityEvent: "+event.toString());
        if(event.getEventType()==AccessibilityEvent.TYPE_VIEW_LONG_CLICKED && event.getClassName().equals())
    }

    @Override
    public void onInterrupt() {
        Log.e(TAG, "onInterrupt: ");
    }
}
