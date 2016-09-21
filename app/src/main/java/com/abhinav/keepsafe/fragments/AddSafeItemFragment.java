package com.abhinav.keepsafe.fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.abhinav.keepsafe.HomeActivity;
import com.abhinav.keepsafe.R;
import com.abhinav.keepsafe.Utils.AccountModel;
import com.abhinav.keepsafe.Utils.KSDatabaseHelper;
import com.abhinav.keepsafe.Utils.KeepSafeKeys;

/**
 * Created by abhinav.sharma on 9/20/2016.
 */
public class AddSafeItemFragment extends BaseFragment implements CompoundButton.OnCheckedChangeListener {

    EditText itemName, password, tranPassword;
    RadioButton radioBank, radioEmail, radioOther;
    private RadioGroup rg;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_safe_item, container, false);
        setupUI(view);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.safe_item_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (validations()) {
            new AddAccountTask(getActivity()).execute(getAccountModel());
            showToast("Item saved");
            getFragmentManager().popBackStack();
        } else showToast("Kuch to gadbad h Daya!! ;)");
        return super.onOptionsItemSelected(item);
    }

    private AccountModel getAccountModel() {
        AccountModel accountModel = new AccountModel();
        accountModel.setAccountName(itemName.getText().toString().trim());
        switch (rg.getCheckedRadioButtonId()) {
            case R.id.radio_bank:
                accountModel.setAccountType(KeepSafeKeys.BANK);
                accountModel.setAccountTranPassword(tranPassword.getText().toString());
                break;
            case R.id.radio_email:
                accountModel.setAccountType(KeepSafeKeys.EMAIL);
                break;
            case R.id.radio_other:
                accountModel.setAccountType(KeepSafeKeys.OTHERS);
                break;
        }
        accountModel.setAccountPassword(password.getText().toString());
        return accountModel;
    }

    private boolean validations() {
        if (itemName == null || TextUtils.getTrimmedLength(itemName.getText().toString()) == 0)
            return false;
        if (password == null || TextUtils.getTrimmedLength(password.getText().toString()) == 0)
            return false;
        if (rg != null){
            if(rg.getCheckedRadioButtonId() == -1)
                return false;
            if(rg.getCheckedRadioButtonId() == R.id.radio_bank && TextUtils.getTrimmedLength(tranPassword.getText().toString())==0)
                return false;
        }
        return true;
    }

    private void setupUI(View view) {
        rg = (RadioGroup) view.findViewById(R.id.radio_group);
        itemName = (EditText) view.findViewById(R.id.et_item_name);
        password = (EditText) view.findViewById(R.id.et_password);
        tranPassword = (EditText) view.findViewById(R.id.et_tran_prassword);
        radioBank = (RadioButton) view.findViewById(R.id.radio_bank);
        radioEmail = (RadioButton) view.findViewById(R.id.radio_email);
        radioOther = (RadioButton) view.findViewById(R.id.radio_other);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.app_bar);
        ((HomeActivity) getActivity()).setupSupportActionBar(toolbar);

        radioBank.setOnCheckedChangeListener(this);
        radioEmail.setOnCheckedChangeListener(this);
        radioOther.setOnCheckedChangeListener(this);

        radioBank.setChecked(true);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            switch (buttonView.getId()) {
                case R.id.radio_bank:
                    tranPassword.setVisibility(View.VISIBLE);
                    break;
                case R.id.radio_email:
                    tranPassword.setVisibility(View.GONE);
                    tranPassword.setText("");
                    break;
                case R.id.radio_other:
                    tranPassword.setVisibility(View.GONE);
                    tranPassword.setText("");
                    break;
            }
        }
    }

    public class AddAccountTask extends AsyncTask<AccountModel, Void, Void> {

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
            ((HomeActivity)mContext).updateKSListFragment();
            super.onPostExecute(aVoid);
        }
    }
}
