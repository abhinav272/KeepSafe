package com.abhinav.keepsafe.fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.widget.Toast;

import com.abhinav.keepsafe.HomeActivity;
import com.abhinav.keepsafe.Utils.AccountModel;
import com.abhinav.keepsafe.Utils.KSDatabaseHelper;

/**
 * Created by abhinav.sharma on 9/20/2016.
 */
public class BaseFragment extends Fragment {

    protected void showToast(String str){
        Toast.makeText(getContext(), str, Toast.LENGTH_SHORT).show();
    }

}
