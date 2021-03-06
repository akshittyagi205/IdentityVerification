package com.example.android.identityverification;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.android.identityverification.MainActivity.MyPREFERENCES;

public class E_KYC_HomeActivity extends AppCompatActivity {

    List<String> spinnerarray;
    List<String> businessId;
    Spinner spinner;
    ArrayAdapter<String> adapter;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e_kyc_home);
        getSupportActionBar().setTitle("eKYC Home");
        spinnerarray = new ArrayList<>();
        businessId = new ArrayList<>();
        submit = (Button) findViewById(R.id.submit);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,spinnerarray);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        spinner.getSelectedItemPosition();
        sendR();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = spinner.getSelectedItemPosition();
                Intent i = new Intent(E_KYC_HomeActivity.this,FillEKYC.class);
                i.putExtra("id",businessId.get(id));
                startActivity(i);
            }
        });
    }

    public void sendR() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Setting up things...");
        progressDialog.show();
        String url = "https://identityverifyapp.herokuapp.com/getbusinessnames/";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONArray ar = new JSONArray(response);
                            for(int i=0;i<ar.length();i++){
                                JSONObject ob = ar.getJSONObject(i);
                                spinnerarray.add(ob.getString("name"));
                                businessId.add(ob.getString("id"));
                            }
                            adapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
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
