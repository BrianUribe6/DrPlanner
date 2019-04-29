package com.cst.drplanner;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class AlarmFragment extends Fragment {

    //variables to store alarm information
    private ArrayList<String> mAlarmTime = new ArrayList<>();
    private ArrayList<String> mTimeMeridiem = new ArrayList<>();
    AlarmRecyclerViewAdapter adapter = new AlarmRecyclerViewAdapter(getContext(), mAlarmTime, mTimeMeridiem);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alarm, container, false);
        FloatingActionButton addAlarmFab = view.findViewById(R.id.fab_add_alarm);
        addAlarmFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addAlarm("01:00","AM");
            }
        });
        initAlarm();
        initRecyclerView(view);
        return view;
    }
    //Test method to add initial values to the recycler view
    private void initAlarm(){
        //Alarm 1
        mAlarmTime.add("12:00");
        mTimeMeridiem.add("AM");
        //Alarm 2
        mAlarmTime.add("12:01");
        mTimeMeridiem.add("PM");
    }
    // sets the adapter and layout to be used by the RecyclerView
    private void initRecyclerView(View view){
        RecyclerView recyclerView = view.findViewById(R.id.recycler_alarm);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
    //Adds a new alarm at the end of the list, and then updates the RecylerView
    private void addAlarm(String time, String meridiem){
        int afterLastAlarm = mAlarmTime.size() + 1;
        mAlarmTime.add(time);
        mTimeMeridiem.add(meridiem);
        adapter.notifyItemInserted(afterLastAlarm);
    }
}
