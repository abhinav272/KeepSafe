package com.abhinav.keepsafe.fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abhinav.keepsafe.R;
import com.abhinav.keepsafe.Utils.AccountModel;
import com.abhinav.keepsafe.Utils.KSDatabaseHelper;
import com.abhinav.keepsafe.adapters.KSAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhinav.sharma on 9/20/2016.
 */
public class KSListFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private KSAdapter ksAdapter;
    private List<AccountModel> accountModels = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_ks_list, container, false);
        setupUI(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fetchUpdatedListFromDB();
    }

    public void fetchUpdatedListFromDB(){
        new AllItemsFetcherTask(getActivity()).execute();
    }

    private void setupUI(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        ksAdapter = new KSAdapter(getActivity(), accountModels);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(ksAdapter);
    }

    public class AllItemsFetcherTask extends AsyncTask<Void, Void, List<AccountModel>>{

        private Context mContext;

        public AllItemsFetcherTask(Context context){
            mContext = context;
        }

        @Override
        protected List<AccountModel> doInBackground(Void... params) {
            return KSDatabaseHelper.getInstance(mContext).getAllItems();
        }

        @Override
        protected void onPostExecute(List<AccountModel> list) {
            super.onPostExecute(list);
            if (list != null) {
                accountModels.clear();
                for (AccountModel model : list) {
                    accountModels.add(model);
                }
                ksAdapter.notifyDataSetChanged();
            }
        }
    }

}
