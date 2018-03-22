package com.example.android.identityverification;

import android.graphics.PorterDuff;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentUrl(Uri.parse(""))
                .build();
        ShareDialog.show(this,content);
        fragmentManager = getSupportFragmentManager();  fragmentManager = getSupportFragmentManager();
        fragment= new HomeFragment();
        home = (ImageView) findViewById(R.id.home);
        verify = (ImageView) findViewById(R.id.verify);
        more = (ImageView) findViewById(R.id.more);
        home.setOnClickListener(this);
        verify.setOnClickListener(this);
        more.setOnClickListener(this);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out);
        transaction.replace(R.id.container,fragment).commit();


    }

    @Override
    public void onFragmentInteraction(Uri uri) {

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
            home.setColorFilter(getResources().getColor(R.color.grey_3), PorterDuff.Mode.SRC_IN);
            fragment= new HomeFragment();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out);
            transaction.replace(R.id.container,fragment).commit();
        }
        else if(view==verify){
            tag="verify";
            verify.setColorFilter(getResources().getColor(R.color.grey_3), PorterDuff.Mode.SRC_IN);
            fragment= new VerifyFragment();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out);
            transaction.replace(R.id.container,fragment).commit();
        }
        else if(view==more){
            tag="more";
            more.setColorFilter(getResources().getColor(R.color.grey_3), PorterDuff.Mode.SRC_IN);
            fragment= new MoreFragment();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out);
            transaction.replace(R.id.container,fragment).commit();
        }
    }
}
