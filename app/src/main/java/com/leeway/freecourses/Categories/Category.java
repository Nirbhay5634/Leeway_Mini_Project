package com.leeway.freecourses.Categories;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.leeway.freecourses.GFGAndroidStudio.AndroidStudioGFG;
import com.leeway.freecourses.GFGDsaStriver.DsaStriverGFG;
import com.leeway.freecourses.GFGJavaAppDev.JavaAppDevGFG;
import com.leeway.freecourses.GFGJavaBackend.JavaBackendGFG;
import com.leeway.freecourses.GFGcp.CpGFG;
import com.leeway.freecourses.R;

public class Category extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

         //BackBtn
        ImageView backBtn = findViewById(R.id.back_pressed);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Category.super.onBackPressed();
            }
        });
    }

    //OnBackPressed
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    // Intent Methods
    public void javaBackend_gfg(View view){
        startActivity(new Intent(Category.this, JavaBackendGFG.class));

    }
    public void cp_gfg(View view) {
        startActivity(new Intent(Category.this, CpGFG.class));
    }
    public void androidDev_gfg(View view) {
        startActivity(new Intent(Category.this, AndroidStudioGFG.class));
    }

    public void javaAppDev_gfg(View view) {
        startActivity(new Intent(Category.this, JavaAppDevGFG.class));
    }

    public void dsaForProfessionals_gfg(View view) {
        startActivity(new Intent(Category.this, DsaStriverGFG.class));
    }
}