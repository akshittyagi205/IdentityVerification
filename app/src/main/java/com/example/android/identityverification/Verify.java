package com.example.android.identityverification;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import static com.example.android.identityverification.MainActivity.MyPREFERENCES;

public class Verify extends AppCompatActivity {

    String output;
    TextView name,yob,gender,city;
    Button quit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.verify_main_activity);
        Bundle extras = getIntent().getExtras();
        output = extras.getString("output");
        name = (TextView) findViewById(R.id.name);
        yob = (TextView) findViewById(R.id.yob);
        gender = (TextView) findViewById(R.id.gender);
        city = (TextView) findViewById(R.id.city);
        name.setText(output.split("=")[2].split(",")[0]);
        yob.setText(output.split("=")[4].split(",")[0]);
        gender.setText(output.split("=")[3].split(",")[0]);
        city.setText(output.split("=")[11].split(",")[0]);
        quit = (Button) findViewById(R.id.quit);
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
