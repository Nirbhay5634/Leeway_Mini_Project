package com.leeway.freecourses.MainScreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.leeway.freecourses.Adapter.CategoriesAdapter;
import com.leeway.freecourses.Adapter.MostViewedAdapter;
import com.leeway.freecourses.Adapter.NewReleaseAdapter;
import com.leeway.freecourses.Adapter.SliderAdapter;
import com.leeway.freecourses.Authentication.Login.LoginActivity;
import com.leeway.freecourses.BuildConfig;
import com.leeway.freecourses.Categories.Category;
import com.leeway.freecourses.HelperClass.CategoriesHelperClass;
import com.leeway.freecourses.HelperClass.MostViewedHelperClass;
import com.leeway.freecourses.HelperClass.NewReleaseHelperClass;
import com.leeway.freecourses.R;
import com.leeway.freecourses.UserDashboard.UserDashboard;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.navigation.NavigationView;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AdView mAdView;
    SliderView sliderView;
    int[] images = {R.drawable.elitmus,
    R.drawable.cbkotlin,
    R.drawable.cbdsa,
    R.drawable.tcs,
    R.drawable.core_subj,
    R.drawable.placement_100,
    R.drawable.dsa_self_placed};

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Menu menu;
    RecyclerView mostViewedRecycler,categoriesRecycler,newReleaseRecycler,recentlyPlayedRecycler;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Remove status bar
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Ads
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
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


        /*-------------Hooks---------------*/

        sliderView = findViewById(R.id.image_slider);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        mostViewedRecycler = findViewById(R.id.most_Viewed_recycler);
        categoriesRecycler = findViewById(R.id.categories_recycler);
        newReleaseRecycler = findViewById(R.id.new_released_recycler);

        SliderAdapter sliderAdapter = new SliderAdapter(images);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();


        // functions will be executed automatically

        mostViewedRecycler();
        categoriesRecycler();
        newReleaseRecycler();

        /*-------------Toolbar---------------*/

        setSupportActionBar(toolbar);

        /*------Hide or show items

        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.nav_logout).setVisible(false);
        menu.findItem(R.id.nav_login).setVisible(false);-------*/

        /*-------------Navigation Drawer Menu---------------*/

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
    }

    // newReleaseRecycler method
    private void newReleaseRecycler() {

        ArrayList<NewReleaseHelperClass> newReleaseHelperClasses = new ArrayList<>();
        newReleaseHelperClasses.add(new NewReleaseHelperClass(R.drawable.winterapp_gfg,"GFG Java App Development"));
        newReleaseHelperClasses.add(new NewReleaseHelperClass(R.drawable.cp_gfg, "GFG Competitive Programming"));
        newReleaseHelperClasses.add(new NewReleaseHelperClass(R.drawable.dsastriver_gfg, "GFG DSA for working professionals"));
        newReleaseHelperClasses.add(new NewReleaseHelperClass(R.drawable.android_dev_gfg, "Android Development"));
        newReleaseHelperClasses.add(new NewReleaseHelperClass(R.drawable.java_backend_gfg, "Java Backend"));

        newReleaseRecycler.setHasFixedSize(true);
        newReleaseRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter = new NewReleaseAdapter(newReleaseHelperClasses);
        newReleaseRecycler.setAdapter(adapter);
    }

    // categoriesRecycler method
    private void categoriesRecycler() {

        ArrayList<CategoriesHelperClass> categoriesHelperClasses = new ArrayList<>();
        categoriesHelperClasses.add(new CategoriesHelperClass(R.drawable.winterapp_gfg,"Geeksforgeeks Java App Development",""));
        categoriesHelperClasses.add(new CategoriesHelperClass(R.drawable.cp_gfg, "Geeksforgeeks Competitive Programming ",""));
        categoriesHelperClasses.add(new CategoriesHelperClass(R.drawable.dsastriver_gfg, "Geeksforgeeks DSA for working professionals",""));
        categoriesHelperClasses.add(new CategoriesHelperClass(R.drawable.android_dev_gfg, "Geeksforgeeks Android Development",""));
        categoriesHelperClasses.add(new CategoriesHelperClass(R.drawable.java_backend_gfg, "Geeksforgeeks Java Backend",""));

        categoriesRecycler.setHasFixedSize(true);
        categoriesRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter = new CategoriesAdapter(categoriesHelperClasses);
        categoriesRecycler.setAdapter(adapter);
    }

    // mostViewedRecycler method
    private void mostViewedRecycler() {
        mostViewedRecycler.setHasFixedSize(true);
        mostViewedRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<MostViewedHelperClass> mostViewedLocations = new ArrayList<>();
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.android_dev_gfg,"GFG Android App Development"));
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.java_backend_gfg,"GFG Backend Development"));
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.cp_gfg,"GFG Competitive Programming"));
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.winterapp_gfg,"GFG Java App Development"));
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.dsastriver_gfg,"DSA for working professionals"));

        adapter = new MostViewedAdapter(mostViewedLocations);
        mostViewedRecycler.setAdapter(adapter);

    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_home:
                startActivity(new Intent(MainActivity.this,MainActivity.class));
                break;
            case R.id.nav_category:
                Intent intent = new Intent(MainActivity.this, Category.class);
                startActivity(intent);
                break;
            case R.id.nav_logout:
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                break;
            case R.id.nav_profile:
                startActivity(new Intent(MainActivity.this, UserDashboard.class));
                break;
            case R.id.nav_share:
                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                    String shareMessage= "\nLet me recommend you this application.We are providing best courses free.Learn something new daily and simply grow with us!\n\n";
                    shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "Share by using"));
                } catch(Exception e) {
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.nav_Rate_us:
                Uri uri = Uri.parse("market://details?id=" + getPackageName());
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                // To count with Play market backstack, After pressing back button,
                // to taken back to our application, we need to add following flags to intent.
                goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                try {
                    startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));
                }
                break;
            case R.id.nav_telegram:
                try {
                    Intent telegram = new Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/+wIEStbD1HO5iY2I1"));
                    telegram.setPackage("org.telegram.messenger");
                    startActivity(telegram);
                }
                catch (Exception e){
                    Intent telegram = new Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/+wIEStbD1HO5iY2I1"));
                    startActivity(telegram);
                }
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void topPicks(View view) {
        startActivity(new Intent(MainActivity.this,Category.class));
    }

    public void personalizedMix(View view) {
        startActivity(new Intent(MainActivity.this,Category.class));
    }

    public void newRelease(View view) {
        startActivity(new Intent(MainActivity.this,Category.class));
    }

    public void refresh_icon(View view) {
        Intent refresh = new Intent(this, MainActivity.class);
        startActivity(refresh);//Start the same Activity
        finish();
    }
}