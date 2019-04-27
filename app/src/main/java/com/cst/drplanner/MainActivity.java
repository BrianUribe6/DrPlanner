package com.cst.drplanner;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        Fragment onSelectedFragment;
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            //Handles the switching between tabs
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    onSelectedFragment = new HomeFragment();
                    break;
                case R.id.navigation_notes:
                    onSelectedFragment = new NotesFragment();
                    break;
                case R.id.navigation_alarm:
                     onSelectedFragment = new AlarmFragment();
                    break;
                case R.id.navigation_life:
                     onSelectedFragment = new LifeFragment();
                    break;
                case R.id.navigation_todo:
                     onSelectedFragment = new TodoFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    onSelectedFragment).commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //Setting home as the first tab
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new HomeFragment()).commit();
    }

}
