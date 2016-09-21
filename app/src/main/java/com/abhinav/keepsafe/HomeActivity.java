package com.abhinav.keepsafe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.abhinav.keepsafe.fragments.AddSafeItemFragment;
import com.abhinav.keepsafe.fragments.KSListFragment;

/**
 * Created by indianrenters on 9/12/16.
 */
public class HomeActivity extends AppCompatActivity implements View.OnClickListener, FragmentManager.OnBackStackChangedListener {

    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setupUI();
    }

    private void setupUI() {
        addListFragment();
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(this);
        getSupportFragmentManager().addOnBackStackChangedListener(this);
    }

    public void setupSupportActionBar(Toolbar toolbar){
        setSupportActionBar(toolbar);
    }

    private void addListFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame_container, new KSListFragment(), KSListFragment.class.getSimpleName());
        fragmentTransaction.addToBackStack(KSListFragment.class.getSimpleName());
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab:
                addWithAddSafeItemFragment();
                break;
        }
    }

    private void addWithAddSafeItemFragment() {
        floatingActionButton.hide();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame_container, new AddSafeItemFragment(), AddSafeItemFragment.class.getSimpleName());
        fragmentTransaction.addToBackStack(AddSafeItemFragment.class.getSimpleName());
        fragmentTransaction.commit();
    }

    public void updateKSListFragment(){
        KSListFragment ksListFragment = (KSListFragment) getSupportFragmentManager().findFragmentByTag(KSListFragment.class.getSimpleName());
        ksListFragment.fetchUpdatedListFromDB();
    }

    @Override
    public void onBackStackChanged() {
        if(getSupportFragmentManager().getBackStackEntryCount()==0)
            finish();
        else if(getSupportFragmentManager().getBackStackEntryCount()==1
                && getSupportFragmentManager().getBackStackEntryAt(0).getName().equals(KSListFragment.class.getSimpleName())){
            floatingActionButton.show();
        }
    }
}
