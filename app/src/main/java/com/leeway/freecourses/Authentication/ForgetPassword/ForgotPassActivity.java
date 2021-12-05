package com.leeway.freecourses.Authentication.ForgetPassword;

import androidx.annotation.NonNull;
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
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.leeway.freecourses.Authentication.Login.LoginActivity;
import com.leeway.freecourses.R;
import com.leeway.freecourses.SplashScreen.InsideLauncher;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;

public class ForgotPassActivity extends AppCompatActivity {

    //variables
    TextInputLayout phoneNumberTextField;
    CountryCodePicker countryCodePicker;
    RelativeLayout progressBar;
    EditText phoneNumberEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);

        //Hooks
        countryCodePicker = findViewById(R.id.country_code_picker);
        phoneNumberTextField = findViewById(R.id.forget_password_phone_number);
        progressBar = findViewById(R.id.forgot_password_progress_bar);
        phoneNumberEditText = findViewById(R.id.forget_password_phone_number_editText);

    }

    public void verifyPhoneNumber(View view) {
        //check Internet Connection
        if (!isConnected(this)) {
            showCustomDialog();
            return;
        }

        //validate username and password
        if (!validateFields()) {
            return;
        }
        progressBar.setVisibility(View.VISIBLE);

        // get data
        String _phoneNumber = phoneNumberTextField.getEditText().getText().toString().trim();
        if (_phoneNumber.charAt(0) == '0') {
            _phoneNumber = _phoneNumber.substring(1);
        }
        final String _completePhoneNumber = "+" + countryCodePicker.getFullNumber() + _phoneNumber;

        //Database
        Query checkUser = FirebaseDatabase.getInstance().getReference("Users").orderByChild("phoneNo").equalTo(_completePhoneNumber);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //If phone Number exists then call OTP to verify that it is his/her phone number
                if(snapshot.exists()){
                    phoneNumberTextField.setError(null);
                    phoneNumberTextField.setErrorEnabled(false);

                    String emailFromDB = snapshot.child(_completePhoneNumber).child("email").getValue(String.class);

                    Intent intent = new Intent(getApplicationContext(), MakeSelection.class);
                    intent.putExtra("email",emailFromDB);
                    intent.putExtra("phoneNo",_completePhoneNumber);
                    //intent.putExtra("whatToDO","updateData");
                    startActivity(intent);
                    finish();

                    progressBar.setVisibility(View.GONE);

                } else{
                    progressBar.setVisibility(View.GONE);
                    phoneNumberTextField.setError("No such user exist!");
                    phoneNumberTextField.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ForgotPassActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean isConnected(ForgotPassActivity forgotPassActivity) {
        ConnectivityManager connectivityManager = (ConnectivityManager) forgotPassActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if ((wifiConn != null && wifiConn.isConnected()) || mobileConn != null && mobileConn.isConnected()) {
            return true;
        } else {
            return false;
        }
    }

    private void showCustomDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ForgotPassActivity.this);
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

    private boolean validateFields() {

        String _phoneNumber = phoneNumberTextField.getEditText().getText().toString().trim();

        if (_phoneNumber.isEmpty()) {
            phoneNumberTextField.setError("Phone number can't be empty");
            phoneNumberTextField.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    public void callBackScreenFromForgetPassword(View view) {
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }
}