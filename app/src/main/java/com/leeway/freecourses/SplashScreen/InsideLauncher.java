package com.leeway.freecourses.SplashScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.leeway.freecourses.Agreement.PrivacyPolicy;
import com.leeway.freecourses.Agreement.TermsAndConditions;
import com.leeway.freecourses.Authentication.Login.LoginActivity;
import com.leeway.freecourses.Authentication.SignUp.SignUpActivity;
import com.leeway.freecourses.R;

public class InsideLauncher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_inside_launcher);

    }

    public void callLoginScreen(View view) {
        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(intent);

//        Pair[] pairs = new Pair[1];
//        pairs[0] = new Pair<View,String>(findViewById(R.id.login1111111),"transition_login");
//
//        ActivityOptions options = null;
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//            options = ActivityOptions.makeSceneTransitionAnimation(InsideLauncher.this,pairs);
//            startActivity(intent,options.toBundle());
//        }else {
//            startActivity(intent);
//        }
    }

    public void callSignUpScreen(View view) {
        Intent intent = new Intent(getApplicationContext(),SignUpActivity.class);
        startActivity(intent);

//        Pair[] pairs = new Pair[1];
//        pairs[0] = new Pair<View,String>(findViewById(R.id.sign_up),"transition_signup");
//
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(InsideLauncher.this,pairs);
//            startActivity(intent,options.toBundle());
//        }
//        else {
//            startActivity(intent);
//        }
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }

    public void privacy_Policy(View view) {
        startActivity(new Intent(InsideLauncher.this, PrivacyPolicy.class));
        finish();
    }

    public void terms_of_service(View view) {
        startActivity(new Intent(InsideLauncher.this, TermsAndConditions.class));
        finish();
    }
}