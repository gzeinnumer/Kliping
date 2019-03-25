package com.gzeinnumer.kliping;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.gzeinnumer.kliping.fragment.HomeFragment;
import com.gzeinnumer.kliping.fragment.KoranFragment;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction fragmentTransaction = null;
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            Fragment fragment = null;
            switch (item.getItemId()) {
//                case R.id.navigation_home:
//                    fragment = new HomeFragment();
//                    break;
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

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FragmentTransaction fragmentTransaction = null;
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = new KoranFragment();
        fragmentTransaction.replace(R.id.frameLayout, fragment,null);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
