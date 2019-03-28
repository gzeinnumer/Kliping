package com.gzeinnumer.kliping.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.gzeinnumer.kliping.R;
import com.gzeinnumer.kliping.fragment.KoranFragment;


import com.gzeinnumer.kliping.fragment.HomeFragment;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public  static final String EMAIL_KEY = "email";
    public  static final String STATUS_KEY = "status";

    public static String email = "";
    public static String status = "";

    LoginActivity.SessionPreference mSession;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction fragmentTransaction = null;
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new HomeFragment();
                    break;
                case R.id.navigation_koran:
                    fragment = new KoranFragment();
                    break;
                //case R.id.navigation_notifications:
            }
            fragmentTransaction.replace(R.id.frameLayout, fragment,null);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainActivity.email = getIntent().getStringExtra(EMAIL_KEY);
        MainActivity.status = getIntent().getStringExtra(STATUS_KEY);

        mSession = new LoginActivity.SessionPreference(MainActivity.this);

        if (mSession.getEmail() == null){
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        } else {
            FragmentTransaction fragmentTransaction = null;
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            Fragment fragment = new HomeFragment();
            fragmentTransaction.replace(R.id.frameLayout, fragment,null);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id =item.getItemId();
        if(id == R.id.logout){
            mSession.logout();
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
