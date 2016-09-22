package com.abhinav.keepsafe.services;

import android.accessibilityservice.AccessibilityService;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.widget.EditText;

import com.abhinav.keepsafe.Utils.KeepSafePrefs;

/**
 * Created by abhinav.sharma on 9/21/2016.
 */
public class KeepSafeService extends AccessibilityService {
    private static final String TAG = KeepSafeService.class.getSimpleName();
    private ClipboardManager clipboard;
    private String oldText = null;
    private String safeText = null;

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        clipboard = (ClipboardManager) getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
        Log.e(TAG, "onServiceConnected: ");
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
//        Log.e(TAG, "onAccessibilityEvent: "+event.toString());
        if ((safeText = KeepSafePrefs.getInstance().getSafeItem(getApplicationContext()))!=null) {
            if (event.getEventType() == AccessibilityEvent.TYPE_VIEW_LONG_CLICKED && event.isPassword()) {
                oldText = clipboard.getPrimaryClip().getItemAt(0).getText().toString();
                ClipData clip = ClipData.newPlainText(null, safeText);
                clipboard.setPrimaryClip(clip);
                Log.e(TAG, "onAccessibilityEvent: yes u are on Password field " + KeepSafePrefs.getInstance().getSafeItem(getApplicationContext()));
            }

            if (event.getEventType() == AccessibilityEvent.TYPE_VIEW_TEXT_SELECTION_CHANGED && event.isPassword()
                    && event.getItemCount() == safeText.length()) {
                ClipData clip = ClipData.newPlainText(null, oldText);
                clipboard.setPrimaryClip(clip);
                KeepSafePrefs.getInstance().setKeepSafeUserPin(getApplicationContext(), null);
                Log.e(TAG, "onAccessibilityEvent: yo man all shit cleaned!!");
            }
        }
    }

    @Override
    public void onInterrupt() {
        Log.e(TAG, "onInterrupt: ");
    }
}
