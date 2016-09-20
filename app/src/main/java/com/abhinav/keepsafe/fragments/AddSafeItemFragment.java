package com.abhinav.keepsafe.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;

import com.abhinav.keepsafe.HomeActivity;
import com.abhinav.keepsafe.R;

/**
 * Created by abhinav.sharma on 9/20/2016.
 */
public class AddSafeItemFragment extends BaseFragment implements CompoundButton.OnCheckedChangeListener {

    EditText itemName, password, tranPassword;
    RadioButton radioBank, radioEmail, radioOther;

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
        if(validations()){
            showToast("Item saved");
            getFragmentManager().popBackStack();
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean validations() {
        return true;
    }

    private void setupUI(View view) {
        itemName = (EditText) view.findViewById(R.id.et_item_name);
        password = (EditText) view.findViewById(R.id.et_password);
        tranPassword = (EditText) view.findViewById(R.id.et_tran_prassword);
        radioBank = (RadioButton) view.findViewById(R.id.radio_bank);
        radioEmail = (RadioButton) view.findViewById(R.id.radio_email);
        radioOther = (RadioButton) view.findViewById(R.id.radio_other);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.app_bar);
        ((HomeActivity)getActivity()).setupSupportActionBar(toolbar);

        radioBank.setOnCheckedChangeListener(this);
        radioEmail.setOnCheckedChangeListener(this);
        radioOther.setOnCheckedChangeListener(this);

        radioBank.setChecked(true);

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
            switch (buttonView.getId()){
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
}
