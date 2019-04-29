package com.cst.drplanner;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class AlarmRecyclerViewAdapter extends RecyclerView.Adapter<AlarmRecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "AlarmRecyclerViewAdapt";
    private ArrayList<String> mTime;
    private ArrayList<String> mMeridiem;
    private Context mContext;
    private int position;

    public AlarmRecyclerViewAdapter(Context mContext, ArrayList<String> mTime, ArrayList<String> mMeridiem) {
        this.mTime = mTime;
        this.mMeridiem = mMeridiem;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_alarm_entry,
                viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    //Populates list with information
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.time.setText(mTime.get(i));
        viewHolder.meridiem.setText(mMeridiem.get(i));
    }

    @Override
    public int getItemCount() {
        return mTime.size();
    }
    //Container for the view and its widgets
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView time;
        TextView meridiem;
        LinearLayout parentLayout;
        Switch alarmState;
        FloatingActionButton fabAddAlarm;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.txt_time);
            meridiem = itemView.findViewById(R.id.txt_meridiem);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            alarmState = itemView.findViewById(R.id.switch_alarm_state);
            fabAddAlarm = itemView.findViewById(R.id.fab_add_alarm);
        }
    }
}
