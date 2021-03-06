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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class AlarmFragment extends Fragment {

    //variables to store alarm information
    private ArrayList<String> mAlarmTime = new ArrayList<>();
    private ArrayList<String> mTimeMeridiem = new ArrayList<>();
    AlarmRecyclerViewAdapter adapter = new AlarmRecyclerViewAdapter(getActivity(), mAlarmTime, mTimeMeridiem);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alarm, container, false);
        FloatingActionButton addAlarmFab = view.findViewById(R.id.fab_add_alarm);
        addAlarmFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addAlarm();
            }
        });
        initRecyclerView(view);
        return view;
    }

    // sets the adapter and layout to be used by the RecyclerView
    private void initRecyclerView(View view){
        RecyclerView recyclerView = view.findViewById(R.id.recycler_alarm);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
    //Adds a new alarm (with the current time) at the end of the list, and updates the RecylerView
    private void addAlarm(){
        //last position in the list
        int afterLastAlarm = mAlarmTime.size() + 1;

        //getting current time
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        String currTime = sdf.format(c.getTime());
        //Separating the time from the AM/PM
        String time = currTime.substring(0, 5);
        String meridiem = "" + currTime.substring(6);
        mAlarmTime.add(time);
        mTimeMeridiem.add(meridiem);
        adapter.notifyItemInserted(afterLastAlarm);
    }
}
