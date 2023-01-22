package com.example.karori.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager.widget.ViewPager;

import com.example.karori.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class SummaryActivity extends AppCompatActivity {
    TabLayout tablayout;
    ViewPager viewPager;
    private View decorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        /*Toolbar toolbar= findViewById(R.id.top_appbar);
        setSupportActionBar(toolbar);*/

        NavHostFragment navHostFragment =(NavHostFragment) getSupportFragmentManager().
                findFragmentById(R.id.nav_host_fragment);

        NavController navController =navHostFragment.getNavController();
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        AppBarConfiguration appBarConfiguration=new AppBarConfiguration.Builder(
                R.id.fragmentHome,R.id.fragmentProfilo, R.id.fragmentSettings).build();

        NavigationUI.setupWithNavController(bottomNavigationView,navController);

        //funzione per togliere la status bar
        decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener(){
            @Override
            public void onSystemUiVisibilityChange(int visibility){
                if(visibility==0)
                    decorView.setSystemUiVisibility(hideSystemBars());
            }
        });



    }
    //funzione per togliere la status bar
    @Override
    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            decorView.setSystemUiVisibility(hideSystemBars());
        }
    }
    //funzione per togliere la status bar
    private int hideSystemBars(){
        return View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY|
                View.SYSTEM_UI_FLAG_FULLSCREEN|
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|
               /*con stable mi rimane lo spazio dell'hide bar
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE|*/
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
    }
}
