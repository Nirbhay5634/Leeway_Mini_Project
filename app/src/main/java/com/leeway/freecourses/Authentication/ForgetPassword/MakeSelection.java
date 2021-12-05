package com.leeway.freecourses.Authentication.ForgetPassword;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;

import com.leeway.freecourses.R;
import com.leeway.freecourses.SplashScreen.InsideLauncher;
import com.google.firebase.auth.FirebaseAuth;

public class MakeSelection extends AppCompatActivity {
    TextView mobile_Des, mail_Des;
    String _phoneNo, _email;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_selection);

        mobile_Des = findViewById(R.id.mobile_des);
        mail_Des = findViewById(R.id.mail_des);
        _phoneNo = getIntent().getStringExtra("phoneNo");
        _email = getIntent().getStringExtra("email");
        mobile_Des.setText(_phoneNo);
        mail_Des.setText(_email);

    }

    public void callOTPScreenFromMakeSelection(View view) {
        //check Internet Connection
        if (!isConnected(this)) {
            showCustomDialog();
            return;
        }
        String _phoneNo = getIntent().getStringExtra("phoneNo");
        Intent intent = new Intent(getApplicationContext(), Forget_Verify_OTP.class);
        intent.putExtra("phoneNo", _phoneNo);
        intent.putExtra("whatToDO","updateData");
        startActivity(intent);
        finish();
    }

    private boolean isConnected(MakeSelection makeSelection) {
        ConnectivityManager connectivityManager = (ConnectivityManager) makeSelection.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if ((wifiConn != null && wifiConn.isConnected()) || mobileConn != null && mobileConn.isConnected()) {
            return true;
        } else {
            return false;
        }
    }

    private void showCustomDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MakeSelection.this);
        builder.setMessage("Please connect to the internet to proceed further!")
                .setCancelable(false)
                .setPositiveButton("Connect", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(getApplicationContext(), InsideLauncher.class));
                        finish();
                    }
                }).show();

    }

    public void callBackScreenFromMakeSelection(View view) {
        startActivity(new Intent(getApplicationContext(),ForgotPassActivity.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),ForgotPassActivity.class));
        finish();
    }
}