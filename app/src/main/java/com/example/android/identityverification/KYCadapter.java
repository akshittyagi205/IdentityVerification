package com.example.android.identityverification;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.identityverification.models.KYCModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class KYCadapter extends RecyclerView.Adapter<KYCadapter.MyViewHolder> {

    private ArrayList<KYCModel> mealList;
    private Context mCtx;
    AlertDialog.Builder alertDialog;


    public KYCadapter(ArrayList<KYCModel> mealList,Context mCtx) {
        this.mealList = mealList;
        this.mCtx = mCtx;
    }




    public class MyViewHolder extends RecyclerView.ViewHolder {


        public TextView name,uid;
        LinearLayout layout;
        public MyViewHolder(View view) {
            super(view);
            name = (TextView)  view.findViewById(R.id.user_name);
            uid = (TextView)  view.findViewById(R.id.uid);
            layout = (LinearLayout) view.findViewById(R.id.layout);
        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_kycfiled, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        final KYCModel meal = mealList.get(position);
        holder.name.setText(meal.getName());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdialog(position);
            }
        });
        holder.uid.setText(meal.getAadhaarNo());
    }

    public void showdialog(int pos){
        final Dialog dialog = new Dialog(mCtx);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.dialog_create_session);
        dialog.setCancelable(true);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        TextView name,uid,yob,gender,city;
        KYCModel model = mealList.get(pos);
        name = (TextView) dialog.findViewById(R.id.name);
        uid = (TextView) dialog.findViewById(R.id.uid);
        yob = (TextView) dialog.findViewById(R.id.yob);
        gender = (TextView) dialog.findViewById(R.id.gender);
        city = (TextView) dialog.findViewById(R.id.city);
        name.setText(model.getName());
        uid.setText(model.getAadhaarNo());
        gender.setText(model.getGender());
        yob.setText(model.getYob());
        city.setText(model.getCity());
        ((AppCompatButton) dialog.findViewById(R.id.btnShare)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }

    @Override
    public int getItemCount()
    {
        return mealList.size();
    }



}