package com.leeway.freecourses.GFGAndroidStudio;

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

public class AndroidStudioGFG extends AppCompatActivity {

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_studio_gfg);

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
                AndroidStudioGFG.super.onBackPressed();
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
        startActivity(new Intent(AndroidStudioGFG.this, Lecture1AndroidStudioGFG.class));
    }
    public void lecture2(View view) {
        startActivity(new Intent(AndroidStudioGFG.this, Lecture2AndroidStudioGFG.class));
    }
    public void lecture3(View view) {
        startActivity(new Intent(AndroidStudioGFG.this, Lecture3AndroidStudioGFG.class));
    }
    public void lecture4(View view) {
        startActivity(new Intent(AndroidStudioGFG.this, Lecture4AndroidStudioGFG.class));
    }
    public void lecture5(View view) {
        startActivity(new Intent(AndroidStudioGFG.this, Lecture5AndroidStudioGFG.class));
    }
    public void lecture6(View view) {
        startActivity(new Intent(AndroidStudioGFG.this, Lecture6AndroidStudioGFG.class));
    }
    public void lecture7(View view) {
        startActivity(new Intent(AndroidStudioGFG.this, Lecture7AndroidStudioGFG.class));
    }
    public void lecture8(View view) {
        startActivity(new Intent(AndroidStudioGFG.this, Lecture8AndroidStudioGFG.class));
    }
    public void lecture9(View view) {
        startActivity(new Intent(AndroidStudioGFG.this, Lecture9AndroidStudioGFG.class));
    }
    public void lecture10(View view) {
        startActivity(new Intent(AndroidStudioGFG.this, Lecture10AndroidStudioGFG.class));
    }
    public void lecture11(View view) {
        startActivity(new Intent(AndroidStudioGFG.this, Lecture11AndroidStudioGFG.class));
    }
    public void lecture12(View view) {
        startActivity(new Intent(AndroidStudioGFG.this, Lecture12AndroidStudioGFG.class));
    }
}
