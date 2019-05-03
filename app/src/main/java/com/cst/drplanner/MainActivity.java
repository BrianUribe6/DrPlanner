package com.cst.drplanner;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    final Fragment homeFragment = new HomeFragment();
    final Fragment notesFragment = new NotesFragment();
    final Fragment alarmFragment = new AlarmFragment();
    final Fragment lifeFragment = new LifeFragment();
    final Fragment todoFragment = new TodoFragment();
    Fragment active = homeFragment;
    FragmentManager fm = getSupportFragmentManager();
    SelectedBundle selectedBundle;

    public interface SelectedBundle {
        void onBundleSelect(Bundle bundle);
    }

    public void setOnBundleSelected(SelectedBundle selectedBundle) {
        this.selectedBundle = selectedBundle;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        Fragment onSelectedFragment;
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fm.beginTransaction().hide(active).show(homeFragment).commit();
                    active = homeFragment;
                    return true;
                case R.id.navigation_notes:
                    fm.beginTransaction().hide(active).show(notesFragment).commit();
                    active = notesFragment;
                    return true;
                case R.id.navigation_alarm:
                    fm.beginTransaction().hide(active).show(alarmFragment).commit();
                    active = alarmFragment;
                    return true;
                case R.id.navigation_calendar:
                    fm.beginTransaction().hide(active).show(lifeFragment).commit();
                    active = lifeFragment;
                    return true;
                case R.id.navigation_todo:
                    fm.beginTransaction().hide(active).show(todoFragment).commit();
                    active = todoFragment;
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Adding all the fragments
        fm.beginTransaction().add(R.id.fragment_container, todoFragment, "todo").hide(todoFragment).commit();
        fm.beginTransaction().add(R.id.fragment_container, lifeFragment, "life").hide(lifeFragment).commit();
        fm.beginTransaction().add(R.id.fragment_container, alarmFragment, "alarm").hide(alarmFragment).commit();
        fm.beginTransaction().add(R.id.fragment_container, notesFragment, "notes").hide(notesFragment).commit();
        //This one does not get hidden because it is the main fragment
        fm.beginTransaction().add(R.id.fragment_container, homeFragment, "home").commit();

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }



}
