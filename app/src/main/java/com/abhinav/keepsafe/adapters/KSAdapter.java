package com.abhinav.keepsafe.adapters;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.abhinav.keepsafe.R;
import com.abhinav.keepsafe.Utils.AccountModel;
import com.abhinav.keepsafe.Utils.KeepSafeKeys;

import java.util.List;

/**
 * Created by abhinav.sharma on 9/21/2016.
 */
public class KSAdapter extends RecyclerView.Adapter<KSAdapter.KSViewHolder> {

    private Context mContext;
    private List<AccountModel> accountModels;
    private LayoutInflater inflater;
    private ClipboardManager clipboard;

    public KSAdapter(Context mContext, List<AccountModel> accountModels){
        this.mContext = mContext;
        this.accountModels = accountModels;
        inflater = LayoutInflater.from(mContext);
        clipboard = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
    }

    @Override
    public KSViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.single_account_layout, parent, false);
        KSViewHolder ksViewHolder = new KSViewHolder(view);
        return ksViewHolder;
    }

    @Override
    public void onBindViewHolder(final KSViewHolder holder, int position) {
        holder.itemName.setText(accountModels.get(position).getAccountName());
        holder.itemType.setText(accountModels.get(position).getAccountType());
        switch (accountModels.get(position).getAccountType()){
            case KeepSafeKeys.BANK:
                holder.tranPassword.setText(accountModels.get(position).getAccountTranPassword());
                break;
            default:
                holder.tranPassword.setVisibility(View.GONE);
                holder.ivTranPass.setVisibility(View.GONE);
                break;
        }
        holder.ivPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipData clip = ClipData.newPlainText(null, holder.password.getText().toString());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(mContext, holder.password.getText().toString()+" copied", Toast.LENGTH_SHORT).show();
            }
        });
        holder.ivTranPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipData clip = ClipData.newPlainText(null, holder.tranPassword.getText().toString());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(mContext, holder.tranPassword.getText().toString()+" copied", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return accountModels.size();
    }

    public class KSViewHolder extends RecyclerView.ViewHolder{
        TextView itemName, itemType, password, tranPassword;
        ImageView ivPass, ivTranPass;
        public KSViewHolder(View itemView) {
            super(itemView);
            itemName = (TextView) itemView.findViewById(R.id.tv_item_name);
            itemType = (TextView) itemView.findViewById(R.id.tv_item_type);
            password = (TextView) itemView.findViewById(R.id.tv_password);
            tranPassword = (TextView) itemView.findViewById(R.id.tv_tran_password);
            ivPass = (ImageView) itemView.findViewById(R.id.iv_password);
            ivTranPass = (ImageView) itemView.findViewById(R.id.iv_tran_password);
        }
    }
}
