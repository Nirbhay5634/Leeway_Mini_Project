package com.leeway.freecourses.GFGJavaAppDev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.leeway.freecourses.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;

public class JavaAppDevGFG extends AppCompatActivity {

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_app_dev_gfg);

        //Ads
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                super.onAdLoaded();
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                // Code to be executed when an ad request fails.
                super.onAdFailedToLoad(adError);
                mAdView.loadAd(adRequest);
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
                super.onAdOpened();
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
                super.onAdClicked();
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
                super.onAdClosed();
            }
        });

        // BackBtn
        ImageView backBtn = findViewById(R.id.back_pressed);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JavaAppDevGFG.super.onBackPressed();
            }
        });
    }

    //onBackPressed
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    // lecture 1-12 method
    public void lecture1(View view) {
        startActivity(new Intent(JavaAppDevGFG.this, Lecture1JavaAppDevGFG.class));
    }
    public void lecture2(View view) {
        startActivity(new Intent(JavaAppDevGFG.this, Lecture2JavaAppDevGFG.class));
    }
    public void lecture3(View view) {
        startActivity(new Intent(JavaAppDevGFG.this, Lecture3JavaAppDevGFG.class));
    }
    public void lecture4(View view) {
        startActivity(new Intent(JavaAppDevGFG.this, Lecture4JavaAppDevGFG.class));
    }
    public void lecture5(View view) {
        startActivity(new Intent(JavaAppDevGFG.this, Lecture5JavaAppDevGFG.class));
    }
    public void lecture6(View view) {
        startActivity(new Intent(JavaAppDevGFG.this, Lecture6JavaAppDevGFG.class));
    }
    public void lecture7(View view) {
        startActivity(new Intent(JavaAppDevGFG.this, Lecture7JavaAppDevGFG.class));
    }
    public void lecture8(View view) {
        startActivity(new Intent(JavaAppDevGFG.this, Lecture8JavaAppDevGFG.class));
    }
    public void lecture9(View view) {
        startActivity(new Intent(JavaAppDevGFG.this, Lecture9JavaAppDevGFG.class));
    }
    public void lecture10(View view) {
        startActivity(new Intent(JavaAppDevGFG.this, Lecture10JavaAppDevGFG.class));
    }
}