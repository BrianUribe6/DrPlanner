package com.cst.drplanner;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    final Fragment notesFragment = new NotesFragment();
    final Fragment alarmFragment = new AlarmFragment();
    final Fragment calendarFragment = new CalendarFragment();
    Fragment active = alarmFragment;
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
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_notes:
                    fm.beginTransaction().hide(active).show(notesFragment).commit();
                    active = notesFragment;
                    return true;
                case R.id.navigation_alarm:
                    fm.beginTransaction().hide(active).show(alarmFragment).commit();
                    active = alarmFragment;
                    return true;
                case R.id.navigation_calendar:
                    fm.beginTransaction().hide(active).show(calendarFragment).commit();
                    active = calendarFragment;
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
        //This one does not get hidden because it is the main fragment
        fm.beginTransaction().add(R.id.fragment_container, alarmFragment, "alarm").commit();
        fm.beginTransaction().add(R.id.fragment_container, notesFragment, "notes").hide(notesFragment).commit();
        fm.beginTransaction().add(R.id.fragment_container, calendarFragment, "calendar").hide(calendarFragment).commit();

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
}
