package com.leeway.freecourses.UserDashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.leeway.freecourses.Databse.SessionManager;
import com.leeway.freecourses.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class UserDashboard extends AppCompatActivity {

    TextView fullNameLabel, usernameLabel;
    TextInputLayout user_fullName, user_email, user_phoneNo,user_username;
    DatabaseReference reference;

    // Global variables to hold user data inside the activity
    String _FULLNAME,_USERNAME, _PHONENO, _EMAIL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        reference = FirebaseDatabase.getInstance().getReference("Users");

        //hooks
        ImageView backBtn = findViewById(R.id.dashboard_back_button);
        user_fullName = findViewById(R.id.full_name_profile);
        user_username = findViewById(R.id.username_profile);
        user_email = findViewById(R.id.email_profile);
        user_phoneNo = findViewById(R.id.phone_number_profile);
        fullNameLabel = findViewById(R.id.fullname_field);
        usernameLabel = findViewById(R.id.username_field);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDashboard.super.onBackPressed();
            }
        });

        SessionManager sessionManager = new SessionManager(this,SessionManager.SESSION_USERSESSION);
        HashMap<String, String> userDetails = sessionManager.getUserDetailFromSession();

        _FULLNAME = userDetails.get(SessionManager.KEY_FULLNAME);
        _USERNAME = userDetails.get(SessionManager.KEY_USERNAME);
        _EMAIL = userDetails.get(SessionManager.KEY_EMAIL);
         _PHONENO = userDetails.get(SessionManager.KEY_PHONENUMBER);

        fullNameLabel.setText(_FULLNAME);
        usernameLabel.setText(_USERNAME);
        user_fullName.getEditText().setText(_FULLNAME);
        user_username.getEditText().setText(_USERNAME);
        user_email.getEditText().setText(_EMAIL);
        user_phoneNo.getEditText().setText(_PHONENO);
    }

    //  update the user data
    public void update(View view) {
        if (isNameChanged() || isUserNameChanged()) {
            Toast.makeText(this, "Data has been updated!\n   Please Login again", Toast.LENGTH_SHORT).show();
            usernameLabel.setText(_USERNAME);
            user_username.getEditText().setText(_USERNAME);
            fullNameLabel.setText(_FULLNAME);
            user_fullName.getEditText().setText(_FULLNAME);

        }else if(isEmailChanged()){
            Toast.makeText(this, "Email can't be changed", Toast.LENGTH_SHORT).show();
            user_email.getEditText().setText(_EMAIL);
        }else if(isPhoneNoChanged()){
            Toast.makeText(this, "Phone Number can't be changed", Toast.LENGTH_SHORT).show();
            user_phoneNo.getEditText().setText(_PHONENO);
        }
        else{
            Toast.makeText(this, "Sorry, Same Data can't be updated!", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isEmailChanged() {
        if (!_EMAIL.equals(user_email.getEditText().getText().toString())) return true;
        else return false;
    }

    private boolean isPhoneNoChanged() {
        if (!_PHONENO.equals(user_phoneNo.getEditText().getText().toString())) return true;
        else return false;
    }

    private boolean isNameChanged() {
        if (!_FULLNAME.equals(user_fullName.getEditText().getText().toString())) {
            reference.child(_PHONENO).child("fullName").setValue(user_fullName.getEditText().getText().toString());
            _FULLNAME = user_fullName.getEditText().getText().toString();
            return true;
        } else {
            return false;
        }
    }

    private boolean isUserNameChanged() {
        if (!_USERNAME.equals(user_username.getEditText().getText().toString())) {
            reference.child(_PHONENO).child("username").setValue(user_username.getEditText().getText().toString());
            _USERNAME = user_username.getEditText().getText().toString();
            return true;
        } else {
            return false;
        }
    }
}