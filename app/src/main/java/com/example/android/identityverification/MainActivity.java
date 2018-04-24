package com.example.android.identityverification;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

public class MainActivity extends AppCompatActivity implements HomeFragment.OnFragmentInteractionListener,View.OnClickListener
        ,VerifyFragment.OnFragmentInteractionListener,MoreFragment.OnFragmentInteractionListener{

    FragmentManager fragmentManager;
    String tag="home";
    Fragment fragment;
    ImageView home,verify,more;
    Bundle extra;
    public static final String MyPREFERENCES = "MyPrefs" ;
    String output;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        extra = getIntent().getExtras();
        getSupportActionBar().setTitle("Home");
        output = extra.getString("output");
        SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("output",output);
        editor.commit();
        home = (ImageView) findViewById(R.id.home);
        verify = (ImageView) findViewById(R.id.verify);
        more = (ImageView) findViewById(R.id.more);
        home.setOnClickListener(this);
        verify.setOnClickListener(this);
        more.setOnClickListener(this);
//        Bundle params = new Bundle();
//        params.putString("message", "This is a test message");
///* make the API call */
//        new GraphRequest(
//                AccessToken.getCurrentAccessToken(),
//                "/me/feed",
//                params,
//                HttpMethod.POST,
//                new GraphRequest.Callback() {
//                    public void onCompleted(GraphResponse response) {
//            /* handle the result */
//                    }
//                }
//        ).executeAsync();

        fragmentManager = getSupportFragmentManager();  fragmentManager = getSupportFragmentManager();
        fragment= HomeFragment.newInstance(output);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out);
        transaction.replace(R.id.container,fragment).commit();




    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.fb){
            ShareLinkContent content = new ShareLinkContent.Builder()
                    .setContentUrl(Uri.parse(""))
                    .build();
            ShareDialog.show(this,content);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if(tag.equals("home")){
            home.setColorFilter(getResources().getColor(R.color.grey_700), PorterDuff.Mode.SRC_IN);
        }
        else if(tag.equals("verify")){
            verify.setColorFilter(getResources().getColor(R.color.grey_700), PorterDuff.Mode.SRC_IN);
        }
        else if(tag.equals("more")){
            more.setColorFilter(getResources().getColor(R.color.grey_700), PorterDuff.Mode.SRC_IN);
        }
        if(view==home){
            tag="home";
            getSupportActionBar().setTitle("Home");
            home.setColorFilter(getResources().getColor(R.color.grey_3), PorterDuff.Mode.SRC_IN);
            fragment=HomeFragment.newInstance(output);
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out);
            transaction.replace(R.id.container,fragment).commit();
        }
        else if(view==verify){
            tag="verify";
            getSupportActionBar().setTitle("Verification");
            verify.setColorFilter(getResources().getColor(R.color.grey_3), PorterDuff.Mode.SRC_IN);
            fragment= new VerifyFragment();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out);
            transaction.replace(R.id.container,fragment).commit();
        }
        else if(view==more){
            tag="more";
            getSupportActionBar().setTitle("More");
            more.setColorFilter(getResources().getColor(R.color.grey_3), PorterDuff.Mode.SRC_IN);
            fragment= new MoreFragment();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out);
            transaction.replace(R.id.container,fragment).commit();
        }
    }
}
