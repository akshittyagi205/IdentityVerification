package com.example.android.identityverification;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONObject;

public class Main2Activity extends AppCompatActivity {

    private IntentIntegrator qrScan;
    Intent intent;
    TextView uid,uidText,name,nameText;
    ImageView image;
    TextView text;
    Button confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().setTitle("Get Started");
        uid = (TextView) findViewById(R.id.uid);
        intent = new Intent(Main2Activity.this,MainActivity.class);
        uidText = (TextView) findViewById(R.id.uidText);
        name = (TextView) findViewById(R.id.name);
        nameText = (TextView) findViewById(R.id.nameText);
        confirm = (Button) findViewById(R.id.confirm);
        image = (ImageView) findViewById(R.id.image);
        text = (TextView) findViewById(R.id.text);
        uid.setVisibility(View.GONE);
        uidText.setVisibility(View.GONE);
        nameText.setVisibility(View.GONE);
        name.setVisibility(View.GONE);
        confirm.setVisibility(View.GONE);
        CardView card =  (CardView) findViewById(R.id.card);
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scanCode();
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(intent);
            }
        });

    }
    public void scanCode(){
        qrScan = new IntentIntegrator(this);
        qrScan.setOrientationLocked(false);
        qrScan.initiateScan();
        qrScan.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        qrScan.setPrompt("Scan your Aadhaar's QR Code");
        qrScan.setCameraId(0);  // Use a specific camera of the device
        qrScan.setBeepEnabled(false);
        qrScan.setBarcodeImageEnabled(false);
        qrScan.initiateScan();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //if qrcode has nothing in it
            if (TextUtils.isEmpty(result.getContents())) {
                Toast.makeText(this,"Unable to fetch the details", Toast.LENGTH_LONG).show();
            } else {
//                Toast.makeText(this,result.getContents() , Toast.LENGTH_LONG).show();
                String res = result.getContents().split("<")[2];
                String[] subres = res.split("=");
                JSONObject ob = new JSONObject();
                String output = "Uid = ";
                int i=0;
                for(String r:subres){
                    Log.d("result",r);
                    if(i==0){
                        i++;
                    }else{
                        String[] subsubr = r.split("\"");
                        int j=0;
                        for(String r1:subsubr){
                            if(j==1) {
                                output += r1+",";
                            }else if(j==2){
                                output +=r1+" = ";
                            }
                            Log.d("sub result "+j,r1);
                            j++;
                        }
                    }
                }
            Log.d("Output",output);
                uid.setVisibility(View.VISIBLE);
                uidText.setVisibility(View.VISIBLE);
                nameText.setVisibility(View.VISIBLE);
                name.setVisibility(View.VISIBLE);
                confirm.setVisibility(View.VISIBLE);
                uid.setText(output.split("=")[1].split(",")[0]);
                name.setText(output.split("=")[2].split(",")[0]);
                image.setImageResource(R.drawable.ic_verified_user);
                intent.putExtra("output",output);
//                intent.putExtra(output.split("=")[0],output.split("=")[1].split(",")[0]);
//                intent.putExtra(output.split("=")[1].split(",")[1],output.split("=")[2].split(",")[0]);
//                intent.putExtra(output.split("=")[2].split(",")[1],output.split("=")[3].split(",")[0]);
//                intent.putExtra(output.split("=")[3].split(",")[1],output.split("=")[4].split(",")[0]);
//                intent.putExtra(output.split("=")[4].split(",")[1],output.split("=")[5].split(",")[0]);
//                intent.putExtra(output.split("=")[5].split(",")[1],output.split("=")[6].split(",")[0]);
//                intent.putExtra(output.split("=")[6].split(",")[1],output.split("=")[7].split(",")[0]);
//                intent.putExtra(output.split("=")[7].split(",")[1],output.split("=")[8].split(",")[0]);
//                intent.putExtra(output.split("=")[8].split(",")[1],output.split("=")[9].split(",")[0]);
//                intent.putExtra(output.split("=")[9].split(",")[1],output.split("=")[10].split(",")[0]);
//                intent.putExtra(output.split("=")[10].split(",")[1],output.split("=")[11].split(",")[0]);
//                intent.putExtra(output.split("=")[11].split(",")[1],output.split("=")[12].split(",")[0]);
                text.setText("Aadhaar Successfully read!\nThe Information read is as below!");
            }
        }
    }
}
