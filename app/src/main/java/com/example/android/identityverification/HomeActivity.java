package com.example.android.identityverification;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

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

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    EditText eMail, password;
    Button submit;
    LinearLayout facebook, google;
    TextView forgotPassword, signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        forgotPassword = (TextView) findViewById(R.id.forgotPassword);
        signUp = (TextView) findViewById(R.id.signUp);

        forgotPassword.setOnClickListener(this);
        signUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == forgotPassword) {
            startActivity(new Intent(this, ForgotPasswordActivity.class));
        } else if (view == signUp) {
            startActivity(new Intent(this, SignUpActivity.class));
        }
    }
}
