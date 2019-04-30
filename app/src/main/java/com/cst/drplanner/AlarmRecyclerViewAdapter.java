package com.cst.drplanner;

import android.app.TimePickerDialog;
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
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AlarmRecyclerViewAdapter extends RecyclerView.Adapter<AlarmRecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "AlarmRecyclerViewAdapt";
    private ArrayList<String> mTime;
    private ArrayList<String> mMeridiem;
    private Context mContext;

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
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        viewHolder.time.setText(mTime.get(i));
        viewHolder.meridiem.setText(mMeridiem.get(i));
        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                final int minute = c.get(Calendar.MINUTE);
                final TimePickerDialog mTimePicker = new TimePickerDialog(view.getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        int pickerHour = timePicker.getHour();
                        int pickerMinutes = timePicker.getMinute();
                        String _24HourTime = pickerHour + ":" + pickerMinutes;
                        String amPm;

                        try {
                            //Converting time to 12 hours format
                            SimpleDateFormat _24HourSDF = new SimpleDateFormat("HH:mm");
                            SimpleDateFormat _12HourSDF = new SimpleDateFormat("hh:mm a");
                            Date _24HourDt = _24HourSDF.parse(_24HourTime);
                            String time = (_12HourSDF.format(_24HourDt));

                            amPm = "" + time.substring(6);
                            //Time without AM/PM
                            time = time.substring(0,5);

                            //Adding time to recyclerView
                            mTime.set(viewHolder.getAdapterPosition(), time.substring(0,5));
                            mMeridiem.set(viewHolder.getAdapterPosition(), amPm);
                            //Updating
                            notifyDataSetChanged();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, hour, minute, false);
                mTimePicker.show();
            }
        });
        //Long click to erase alarm
        viewHolder.parentLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                int position = viewHolder.getAdapterPosition();
                deleteAlarm(position);
                //FIXME: Add method to cancel alarm once AlarmManager is implemented
                return true;
            }
        });
    }

    private void deleteAlarm(int position) {
        mTime.remove(position);
        mMeridiem.remove(position);
        notifyItemRemoved(position);
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
