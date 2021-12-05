package com.leeway.freecourses.GFGDsaStriver;

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

public class DsaStriverGFG extends AppCompatActivity {

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dsa_striver_gfg);

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
                DsaStriverGFG.super.onBackPressed();
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
        startActivity(new Intent(DsaStriverGFG.this, Lecture1DsaStriverGFG.class));
    }
    public void lecture2(View view) {
        startActivity(new Intent(DsaStriverGFG.this, Lecture2DsaStriverGFG.class));
    }
    public void lecture3(View view) {
        startActivity(new Intent(DsaStriverGFG.this, Lecture3DsaStriverGFG.class));
    }
    public void lecture4(View view) {
        startActivity(new Intent(DsaStriverGFG.this, Lecture4DsaStriverGFG.class));
    }
    public void lecture5(View view) {
        startActivity(new Intent(DsaStriverGFG.this, Lecture5DsaStriverGFG.class));
    }
    public void lecture6(View view) {
        startActivity(new Intent(DsaStriverGFG.this, Lecture6DsaStriverGFG.class));
    }
    public void lecture7(View view) {
        startActivity(new Intent(DsaStriverGFG.this, Lecture7DsaStriverGFG.class));
    }
    public void lecture8(View view) {
        startActivity(new Intent(DsaStriverGFG.this, Lecture8DsaStriverGFG.class));
    }
    public void lecture9(View view) {
        startActivity(new Intent(DsaStriverGFG.this, Lecture9DsaStriverGFG.class));
    }
    public void lecture10(View view) {
        startActivity(new Intent(DsaStriverGFG.this, Lecture10DsaStriverGFG.class));
    }
    public void lecture11(View view) {
        startActivity(new Intent(DsaStriverGFG.this, Lecture11DsaStriverGFG.class));
    }
    public void lecture12(View view) {
        startActivity(new Intent(DsaStriverGFG.this, Lecture12DsaStriverGFG.class));
    }
    public void lecture13(View view) {
        startActivity(new Intent(DsaStriverGFG.this, Lecture13DsaStriverGFG.class));
    }
    public void lecture14(View view) {
        startActivity(new Intent(DsaStriverGFG.this, Lecture14DsaStriverGFG.class));
    }
}