package com.leeway.freecourses.Authentication.SignUp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
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

public class SignUp2ndClass extends AppCompatActivity {
    ImageView backBtn;
    ScrollView scrollView;
    TextInputLayout phoneNumber;
    CountryCodePicker countryCodePicker;
    RelativeLayout progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up2nd_class);

        backBtn = findViewById(R.id.signup_back_button);
        countryCodePicker = findViewById(R.id.country_code_picker);
        scrollView = findViewById(R.id.signup_2nd_screen_scrool_view);
        phoneNumber = findViewById(R.id.signup_phone_number);
        progressBar = findViewById(R.id.sign_up_progress_bar);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
                finish();
            }
        });
    }

    public void callVerifyOTPScreen(View view) {

        //check Internet Connection
        if (!isConnected(this)) {
            showCustomDialog();
            return;
        }

        //validate fields
        if (!validatePhoneNumber()) {
            return;
        }
        //validation succeeded and now move to next screen to verify phone number and save data
        progressBar.setVisibility(View.VISIBLE);


        //Get all values passed from previous screens using intent
        String _fullName = getIntent().getStringExtra("fullName");
        String _email = getIntent().getStringExtra("email");
        String _username = getIntent().getStringExtra("username");
        String _password = getIntent().getStringExtra("password");

        String _getUserEnteredPhoneNumber = phoneNumber.getEditText().getText().toString().trim(); //Get phone number
        //Remove first zero if entered!
        if (_getUserEnteredPhoneNumber.charAt(0) == '0') {
            _getUserEnteredPhoneNumber = _getUserEnteredPhoneNumber.substring(1);
        }
        String _phoneNo = "+" + countryCodePicker.getFullNumber() + _getUserEnteredPhoneNumber;

        //Database
        Query checkUser = FirebaseDatabase.getInstance().getReference("Users").orderByChild("phoneNo").equalTo(_phoneNo);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    phoneNumber.setError("User Exist");
                    phoneNumber.setErrorEnabled(true);
                    progressBar.setVisibility(View.GONE);
                } else{

                    Intent intent = new Intent(getApplicationContext(), VerifyOTP.class);

                    //Pass all fields to the next activity
                    intent.putExtra("fullName", _fullName);
                    intent.putExtra("email", _email);
                    intent.putExtra("username", _username);
                    intent.putExtra("password", _password);
                    intent.putExtra("phoneNo", _phoneNo);
                    // This is to identify that which action should OTP perform after verification.


                    //Add Shared Animation
                    Pair[] pairs = new Pair[1];
                    pairs[0] = new Pair<View, String>(scrollView, "transition_OTP_screen");
                    ActivityOptions options = null;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                        options = ActivityOptions.makeSceneTransitionAnimation(SignUp2ndClass.this, pairs);
                        startActivity(intent, options.toBundle());
                    }else {
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(SignUp2ndClass.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



    }

    private boolean isConnected(SignUp2ndClass signUp2ndClass) {
        ConnectivityManager connectivityManager = (ConnectivityManager) signUp2ndClass.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if ((wifiConn != null && wifiConn.isConnected()) || mobileConn != null && mobileConn.isConnected()) {
            return true;
        } else {
            return false;
        }
    }

    private void showCustomDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SignUp2ndClass.this);
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

    public void callLoginFromSignUp(View view) {
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }

    private boolean validatePhoneNumber() {
        String val = phoneNumber.getEditText().getText().toString().trim();
        String checkPhoneNumber = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
                + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
                + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$";
        if (val.isEmpty()) {
            phoneNumber.setError("Enter valid phone number");
            return false;
        } else if (!val.matches(checkPhoneNumber)) {
            phoneNumber.setError("Invalid Phone Number!");
            return false;
        } else {
            phoneNumber.setError(null);
            phoneNumber.setErrorEnabled(false);
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),SignUpActivity.class));
        finish();
    }
}