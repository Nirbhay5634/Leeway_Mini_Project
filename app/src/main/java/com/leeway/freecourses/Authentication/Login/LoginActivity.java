package com.leeway.freecourses.Authentication.Login;

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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.leeway.freecourses.Authentication.ForgetPassword.ForgotPassActivity;
import com.leeway.freecourses.Authentication.SignUp.SignUpActivity;
import com.leeway.freecourses.SplashScreen.InsideLauncher;
import com.leeway.freecourses.MainScreen.MainActivity;
import com.leeway.freecourses.R;
import com.leeway.freecourses.Databse.SessionManager;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    //Variables
    CountryCodePicker countryCodePicker;
    TextInputLayout phoneNumber, password;
    RelativeLayout progressBar;
    ImageView backBtn;
    CheckBox rememberMe;
    EditText phoneNumberEditText,passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        //Hooks
        countryCodePicker = findViewById(R.id.login_country_code_picker);
        phoneNumber = findViewById(R.id.login_phone_number);
        password = findViewById(R.id.login_password);
        progressBar = findViewById(R.id.login_progress_bar);
        backBtn = findViewById(R.id.login_back_button);
        rememberMe = findViewById(R.id.remember_me);
        phoneNumberEditText = findViewById(R.id.login_phone_number_editText);
        passwordEditText = findViewById(R.id.login_password_editText);

        //check weather phone no and password is already saved in Shared Preferences or not
        SessionManager sessionManager = new SessionManager(LoginActivity.this,SessionManager.SESSION_REMEMBERME);
        if(sessionManager.checkRememberMe()){

            HashMap<String,String> rememberMeDetails = sessionManager.getRememberMeDetailFromSession();
            phoneNumberEditText.setText(rememberMeDetails.get(SessionManager.KEY_SESSIONPHONENUMBER));
            passwordEditText.setText(rememberMeDetails.get(SessionManager.KEY_SESSIONPASSWORD));
        }


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,InsideLauncher.class));
                finish();
            }
        });


    }

    public void callForgetPassword(View view) {
        startActivity(new Intent(LoginActivity.this, ForgotPassActivity.class));
        finish();
    }

    //login the user in app!
    public void letTheUserLoggedIn(View view) {

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
        String _phoneNumber = phoneNumber.getEditText().getText().toString().trim();
        String _password = password.getEditText().getText().toString().trim();
        //Remove first zero if entered!
        if (_phoneNumber.charAt(0) == '0') {
            _phoneNumber = _phoneNumber.substring(1);
        }
        final String _completePhoneNumber = "+" + countryCodePicker.getFullNumber() + _phoneNumber;

        if(rememberMe.isChecked()){
            SessionManager sessionManager = new SessionManager(LoginActivity.this,SessionManager.SESSION_REMEMBERME);
            sessionManager.createRememberMeSession(_phoneNumber,_password);
        }

        //Database
        Query checkUser = FirebaseDatabase.getInstance().getReference("Users").orderByChild("phoneNo").equalTo(_completePhoneNumber);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    phoneNumber.setError(null);
                    phoneNumber.setErrorEnabled(false);

                    String systemPassword = snapshot.child(_completePhoneNumber).child("password").getValue(String.class);
                    if (systemPassword.equals(_password)) {
                        password.setError(null);
                        password.setErrorEnabled(false);
                        progressBar.setVisibility(View.GONE);


                        String fullNameFromDB = snapshot.child(_completePhoneNumber).child("fullName").getValue(String.class);
                        String usernameFromDB = snapshot.child(_completePhoneNumber).child("username").getValue(String.class);
                        String phoneNoFromDB = snapshot.child(_completePhoneNumber).child("phoneNo").getValue(String.class);
                        String emailFromDB = snapshot.child(_completePhoneNumber).child("email").getValue(String.class);

                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);

                        SessionManager sessionManager = new SessionManager(LoginActivity.this,SessionManager.SESSION_USERSESSION);
                        sessionManager.createLoginSession(fullNameFromDB,usernameFromDB,emailFromDB,phoneNoFromDB);
                        startActivity(intent);
                        finish();


                    } else {
                        progressBar.setVisibility(View.GONE);
                        password.setError("Wrong Password!");
                        password.requestFocus();
                    }
                } else {
                    progressBar.setVisibility(View.GONE);
                    phoneNumber.setError("No such User Exist!");
                    phoneNumber.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(LoginActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean isConnected(LoginActivity loginActivity) {
        ConnectivityManager connectivityManager = (ConnectivityManager) loginActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if ((wifiConn != null && wifiConn.isConnected()) || mobileConn != null && mobileConn.isConnected()) {
            return true;
        } else {
            return false;
        }
    }

    private void showCustomDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
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

        String _phoneNumber = phoneNumber.getEditText().getText().toString().trim();
        String _password = password.getEditText().getText().toString().trim();

        if (_phoneNumber.isEmpty()) {
            phoneNumber.setError("Phone number can't be empty");
            phoneNumber.requestFocus();
            return false;
        } else if (_password.isEmpty()) {
            password.setError("Password can't be empty");
            password.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    public void callSignUpFromLogin(View view) {
        startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(LoginActivity.this,InsideLauncher.class));
        finish();
    }
}