package com.abhinav.keepsafe.fragments;

import android.support.v4.app.Fragment;
import android.widget.Toast;

/**
 * Created by abhinav.sharma on 9/20/2016.
 */
public class BaseFragment extends Fragment {

    protected void showToast(String str){
        Toast.makeText(getContext(), str, Toast.LENGTH_SHORT).show();
    }

}
