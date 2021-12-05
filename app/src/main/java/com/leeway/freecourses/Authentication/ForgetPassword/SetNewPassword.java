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
import android.widget.RelativeLayout;

import com.leeway.freecourses.R;
import com.leeway.freecourses.SplashScreen.InsideLauncher;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SetNewPassword extends AppCompatActivity {

    //variables
    RelativeLayout progressBar;
    TextInputLayout newPassword,confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_new_password);

        //Hooks
        progressBar = findViewById(R.id.set_new_password_progress_bar);
        newPassword = findViewById(R.id.new_password);
        confirmPassword= findViewById(R.id.confirm_password);
    }

    public void goToHomeFromSetNewPassword(View view) {
        startActivity(new Intent(getApplicationContext(),ForgotPassActivity.class));
        finish();
    }

    public void setNewPasswordBtn(View view) {

        //check Internet Connection
        if (!isConnected(this)) {
            showCustomDialog();
            return;
        }

        //validate password and confirm password
        if (!validatePassword() || !validateConfirmPassword()) {
            return;
        }
        progressBar.setVisibility(View.VISIBLE);

        //Get data from fields
        String _newPassword = newPassword.getEditText().getText().toString().trim();
        String _phoneNumber = getIntent().getStringExtra("phoneNo");

        // Update Data in firebase and in sessions
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child(_phoneNumber).child("password").setValue(_newPassword);

        startActivity(new Intent(getApplicationContext(),ForgetPasswordSuccessMessage.class));
        finish();

    }

    private boolean isConnected(SetNewPassword setNewPassword) {
        ConnectivityManager connectivityManager = (ConnectivityManager) setNewPassword.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if ((wifiConn != null && wifiConn.isConnected()) || mobileConn != null && mobileConn.isConnected()) {
            return true;
        } else {
            return false;
        }
    }

    private void showCustomDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SetNewPassword.this);
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

    private boolean validatePassword() {

        String _newPassword = newPassword.getEditText().getText().toString().trim();
        String checkPassword = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{6,}$";

        if (_newPassword.isEmpty()) {
            newPassword.setError("Phone number can't be empty");
            newPassword.requestFocus();
            return false;
        }else if(_newPassword.length()<6){
            newPassword.setError("Password must be at least 6 characters");
            return false;
        }else if (_newPassword.length() > 15) {
            newPassword.setError("Password is too large!");
            return false;
        }else if (!_newPassword.matches(checkPassword)) {
            newPassword.setError("Password should contain at least 1 uppercase, lowercase english letter, digit, special character!");
            return false;
        }
        else {
            return true;
        }
    }

    private boolean validateConfirmPassword() {
        String _newPassword = newPassword.getEditText().getText().toString().trim();
        String _confirmPassword = confirmPassword.getEditText().getText().toString().trim();
        if(!_newPassword.equals(_confirmPassword)){
            confirmPassword.setError("Password doesn't match!");
            return false;
        } else{
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),ForgotPassActivity.class));
        finish();
    }
}