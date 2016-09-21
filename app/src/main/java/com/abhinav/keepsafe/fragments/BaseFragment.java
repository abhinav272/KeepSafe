package com.abhinav.keepsafe.fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.widget.Toast;

import com.abhinav.keepsafe.Utils.AccountModel;
import com.abhinav.keepsafe.Utils.KSDatabaseHelper;

/**
 * Created by abhinav.sharma on 9/20/2016.
 */
public class BaseFragment extends Fragment {

    protected void showToast(String str){
        Toast.makeText(getContext(), str, Toast.LENGTH_SHORT).show();
    }

    protected void addAccountToDB(AccountModel accountModel){
        new AddAccountTask(getContext()).execute(accountModel);
    }

    public class AddAccountTask extends AsyncTask<AccountModel, Void, Void>{

        private Context mContext;

        public AddAccountTask(Context context) {
            mContext = context;
        }

        @Override
        protected Void doInBackground(AccountModel... params) {
            KSDatabaseHelper.getInstance(mContext).saveAccountData(params[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            // todo -- update recyccler view for added account
            super.onPostExecute(aVoid);
        }
    }

}
