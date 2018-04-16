package com.example.android.identityverification.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.android.identityverification.KYCFiled;
import com.example.android.identityverification.R;
import com.example.android.identityverification.models.kycfiled;

import java.util.ArrayList;

/**
 * Created by connect2subrat on 08-02-2018.
 */

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemListHolder> {

    private ArrayList<kycfiled> itemsList;
    private Context mContext;

    public ItemsAdapter(Context context, ArrayList<kycfiled> itemsList) {
        this.itemsList = itemsList;
        this.mContext = context;
    }

    @Override
    public ItemListHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_kycfiled, null);
        ItemListHolder itemListHolder = new ItemListHolder(view, mContext, itemsList);
        return itemListHolder;
    }

    @Override
    public void onBindViewHolder(ItemListHolder holder, int position) {

        kycfiled item = itemsList.get(position);
        holder.name.setText(item.getName());
        holder.aadhaarNo.setText(item.getAadhaarNo());

    }

    @Override
    public int getItemCount() {
        return (null != itemsList ? itemsList.size() : 0);
    }

    public class ItemListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected TextView name, aadhaarNo;
        ArrayList<kycfiled> itemsList = new ArrayList<kycfiled>();
        Context mContext;


        public ItemListHolder(View view, Context mContext, ArrayList<kycfiled> itemsList) {
            super(view);
            this.itemsList = itemsList;
            this.mContext = mContext;

            view.setOnClickListener(this);

            this.name = (TextView) view.findViewById(R.id.user_name);
            this.aadhaarNo = (TextView) view.findViewById(R.id.aadhaar_no);

        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
//            kycfiled itemList = this.itemsList.get(position);
//            Intent intent = new Intent(this.mContext, RecipeActivity.class);
//            intent.putExtra("INAME", itemList.getName());
//            this.mContext.startActivity(intent);
        }
    }

}