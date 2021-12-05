package com.leeway.freecourses.GFGcp;

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

public class CpGFG extends AppCompatActivity {

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cp_gfg);

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
                CpGFG.super.onBackPressed();
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
        startActivity(new Intent(CpGFG.this, Lecture1CpGFG.class));
    }
    public void lecture2(View view) {
        startActivity(new Intent(CpGFG.this, Lecture2CpGFG.class));
    }
    public void lecture3(View view) {
        startActivity(new Intent(CpGFG.this, Lecture3CpGFG.class));
    }
    public void lecture4(View view) {
        startActivity(new Intent(CpGFG.this, Lecture4CpGFG.class));
    }
    public void lecture5(View view) {
        startActivity(new Intent(CpGFG.this, Lecture5CpGFG.class));
    }
    public void lecture6(View view) {
        startActivity(new Intent(CpGFG.this, Lecture6CpGFG.class));
    }
    public void lecture7(View view) {
        startActivity(new Intent(CpGFG.this, Lecture7CpGFG.class));
    }
    public void lecture8(View view) {
        startActivity(new Intent(CpGFG.this, Lecture8CpGFG.class));
    }
    public void lecture9(View view) {
        startActivity(new Intent(CpGFG.this, Lecture9CpGFG.class));
    }
    public void lecture10(View view) {
        startActivity(new Intent(CpGFG.this, Lecture10CpGFG.class));
    }
    public void lecture11(View view) {
        startActivity(new Intent(CpGFG.this, Lecture11CpGFG.class));
    }
    public void lecture12(View view) {
        startActivity(new Intent(CpGFG.this, Lecture12CpGFG.class));
    }
    public void lecture13(View view) {
        startActivity(new Intent(CpGFG.this, Lecture13CpGFG.class));
    }
    public void lecture14(View view) {
        startActivity(new Intent(CpGFG.this, Lecture14CpGFG.class));
    }
    public void lecture15(View view) {
        startActivity(new Intent(CpGFG.this, Lecture15CpGFG.class));
    }
    public void lecture16(View view) {
        startActivity(new Intent(CpGFG.this, Lecture16CpGFG.class));
    }
    public void lecture17(View view) {
        startActivity(new Intent(CpGFG.this, Lecture17CpGFG.class));
    }
    public void lecture18(View view) {
        startActivity(new Intent(CpGFG.this, Lecture18CpGFG.class));
    }
    public void lecture19(View view) {
        startActivity(new Intent(CpGFG.this, Lecture19CpGFG.class));
    }
    public void lecture20(View view) {
        startActivity(new Intent(CpGFG.this, Lecture20CpGFG.class));
    }
    public void lecture21(View view) {
        startActivity(new Intent(CpGFG.this, Lecture21CpGFG.class));
    }
    public void lecture22(View view) {
        startActivity(new Intent(CpGFG.this, Lecture22CpGFG.class));
    }
    public void lecture23(View view) {
        startActivity(new Intent(CpGFG.this, Lecture23CpGFG.class));
    }
    public void lecture24(View view) {
        startActivity(new Intent(CpGFG.this, Lecture24CpGFG.class));
    }
}