package com.leeway.freecourses.Agreement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.leeway.freecourses.R;
import com.leeway.freecourses.SplashScreen.InsideLauncher;

public class PrivacyPolicy extends AppCompatActivity {

    WebView webView;
    public String fileName = "privacy_policy.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);

        webView = findViewById(R.id.privacy_policy_webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/" + fileName);
    }

    public void back_pressed(View view) {
        startActivity(new Intent(PrivacyPolicy.this, InsideLauncher.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(PrivacyPolicy.this, InsideLauncher.class));
        finish();
    }
}