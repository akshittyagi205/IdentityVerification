package com.example.android.identityverification;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.Map;

import static com.example.android.identityverification.MainActivity.MyPREFERENCES;

/*
* Some tips:
* Same file pr kbhi ek sath kaam nhi krna
* push and pull k liye ye upr do arrows jo h wo use krne h
* push and pull ka mtlb to pta hi hoga
* jb b kuch kaam kro to push krdo
* and humehsa kaam shuru krne se pehle pull krlo
* push krke b dikha dun ek baar?
* dikah de
* push krne k liye kuch change krna zaroori hai
* aagya smjh sbh?
* nd haan
* n jb koi b new file bnegi project me to wo puchega ki add to git?
* to usko yes krna> hok
* ab start kr le
* */

public class HomeActivity extends AppCompatActivity  {
    EditText eMail, password;
    Button submit,signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        eMail = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        submit = (Button) findViewById(R.id.submit);
        signup = (Button) findViewById(R.id.signup);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(eMail.getText().toString().trim())||TextUtils.isEmpty(password.getText().toString().trim())){
                    Toast.makeText(getApplicationContext(),"Empty fields not allowed!",Toast.LENGTH_SHORT).show();
                }else {
                    sendR(eMail.getText().toString().trim(),password.getText().toString().trim());
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(HomeActivity.this,SignUpActivity.class));
            }
        });
    }

    public void sendR(final String email,final String pass) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Signing In...");
        progressDialog.show();
        String url = "https://identityverifyapp.herokuapp.com/businesslogin/";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        if(response.equalsIgnoreCase("login failed")){
                            Toast.makeText(getApplicationContext(),"Some error occured! Try again!",Toast.LENGTH_SHORT).show();
                        }else{
                            SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.putInt("isbusiness",1);
                            editor.putInt("isloggedin",1);
                            editor.putString("id",email);
                            editor.commit();
                            finish();
                            startActivity(new Intent(HomeActivity.this,BusinessDashboardActivity.class));
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
                params.put("email",email);
                params.put("password",pass);
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
