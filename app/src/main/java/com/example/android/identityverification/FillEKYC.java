package com.example.android.identityverification;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import static com.example.android.identityverification.MainActivity.MyPREFERENCES;

public class FillEKYC extends AppCompatActivity {

    TextView name,uid,gender,yob,city;
    Button submit;
    String output,id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_ekyc);
        Bundle extras = getIntent().getExtras();
        id = extras.getString("id");
        name = (TextView) findViewById(R.id.name);
        uid = (TextView) findViewById(R.id.uid);
        gender = (TextView) findViewById(R.id.gender);
        yob = (TextView) findViewById(R.id.yob);
        city = (TextView) findViewById(R.id.city);
        submit = (Button) findViewById(R.id.submit);
        SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        output=sharedpreferences.getString("output","null");
        name.setText(output.split("=")[2].split(",")[0]);
        yob.setText(output.split("=")[4].split(",")[0]);
        gender.setText(output.split("=")[3].split(",")[0]);
        city.setText(output.split("=")[11].split(",")[0]);
        uid.setText(output.split("=")[1].split(",")[0]);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendR();
            }
        });
    }

    public void sendR() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Signing Up...");
        progressDialog.show();
        String url = "https://identityverifyapp.herokuapp.com/fillekyc/";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        if(response.equalsIgnoreCase("data saved")){
                            Toast.makeText(getApplicationContext(),"KYC Filled Successfully!",Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(getApplicationContext(),"Some error occured!",Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(),"Something went wrong!\nCheck your Internet connection and try again..", Toast.LENGTH_LONG).show();
                        //Toast.makeText(MedicineData.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String, String> params = new HashMap<>();
                params.put("id",id);
                params.put("data",output);
                return params;
            }

        };

        int MY_SOCKET_TIMEOUT_MS = 50000;
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
