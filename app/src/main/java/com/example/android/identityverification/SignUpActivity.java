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

public class SignUpActivity extends AppCompatActivity {

    EditText name,email,password,confirmPass;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        confirmPass = (EditText) findViewById(R.id.confirmPass);
        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(name.getText().toString().trim())||TextUtils.isEmpty(email.getText().toString().trim())||TextUtils.isEmpty(password.getText().toString().trim())||TextUtils.isEmpty(confirmPass.getText().toString().trim())){
                    Toast.makeText(getApplicationContext(),"Empty fields not allowed",Toast.LENGTH_SHORT).show();
                }else{
                    if(password.getText().toString().trim().equals(confirmPass.getText().toString().trim())){
                        sendR(name.getText().toString().trim(),email.getText().toString().trim(),password.getText().toString().trim());
                    }else{
                        Toast.makeText(getApplicationContext(),"Password doesn't match!",Toast.LENGTH_SHORT).show();
                        password.setText("");
                        confirmPass.setText("");
                    }
                }
            }
        });
    }

    public void sendR(final String name,final String email,final String pass) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Signing Up...");
        progressDialog.show();
        String url = "https://identityverifyapp.herokuapp.com/businesssignup/";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        if(response.equalsIgnoreCase("error")){
                            Toast.makeText(getApplicationContext(),"Some error occured! Try again!",Toast.LENGTH_SHORT).show();
                        }else{
                            SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.putInt("isbusiness",1);
                            editor.putInt("isloggedin",1);
                            editor.putString("id",email);
                            editor.commit();
                            finish();
                            startActivity(new Intent(SignUpActivity.this,BusinessDashboardActivity.class));
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
                params.put("name",name);
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
