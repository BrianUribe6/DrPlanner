package com.cst.drplanner;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
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
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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
        return new ViewHolder(view);
    }

    @Override
    //Populates list with information
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        viewHolder.time.setText(mTime.get(i));
        viewHolder.meridiem.setText(mMeridiem.get(i));

        // Click to modify alarm
        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                final int minute = c.get(Calendar.MINUTE);
                setAlarm(viewHolder, view, hour, minute);
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
        //removing alarm from arrayList
        mTime.remove(position);
        mMeridiem.remove(position);
        //Updating view
        notifyItemRemoved(position);
    }
    private  void updateAlarm(@NonNull final ViewHolder viewHolder,Context context, String time, String AM_PM, long timeInMilliseconds){
        //Adding time to recyclerView
        mTime.set(viewHolder.getAdapterPosition(), time.substring(0,5));
        mMeridiem.set(viewHolder.getAdapterPosition(), AM_PM);
        viewHolder.alarmState.setChecked(true);
        //Updating text
        notifyDataSetChanged();

        //Activating alarm
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        int requestCode = viewHolder.getAdapterPosition();
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, 0);
        alarmManager.setRepeating(AlarmManager.RTC, timeInMilliseconds, AlarmManager.INTERVAL_DAY, pendingIntent);
        Toast.makeText(context, "Alarm is set at " + time + AM_PM, Toast.LENGTH_SHORT).show();
    }
    private void setAlarm(@NonNull final ViewHolder viewHolder, View view, int hour, int minute){
        /* Opens the Time Picker Dialog to allow the user to set the time of the alarm. This time
        is retrieved by android in 24 hours format. Conversion to 12 hour SDF was also implemented
        for this matter. Once the conversion is done this method sets the current alarm selected to
        the user's desired time.
        @param viewHolder corresponds to the viewHolder of the current selected widget
        @param view corresponds to the view of the onClick listener.
        * */
        final Context context = view.getContext();
        final TimePickerDialog mTimePicker = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                int pickerHour = timePicker.getHour();
                int pickerMinutes = timePicker.getMinute();
                //Used to fire the alarm precisely
                long timeInMillis = TimeUnit.HOURS.toMillis(pickerHour) + TimeUnit.MINUTES.toMillis(pickerMinutes);

                String _24HourTime = pickerHour + ":" + pickerMinutes;
                String amPm;

                try {
                    //Converting time to 12 hours format
                    SimpleDateFormat _24HourSDF = new SimpleDateFormat("HH:mm");
                    SimpleDateFormat _12HourSDF = new SimpleDateFormat("hh:mm a");
                    Date _24HourDt = _24HourSDF.parse(_24HourTime);
                    String time = (_12HourSDF.format(_24HourDt));

                    amPm = "" + time.substring(6);
                    //Hour and Minutes without AM/PM
                    time = time.substring(0,5);

                    //Setting alarm
                    updateAlarm(viewHolder, context, time, amPm, timeInMillis);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, hour, minute, false);
        mTimePicker.show();
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
        FloatingActionButton removeAlarm;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.txt_time);
            meridiem = itemView.findViewById(R.id.txt_meridiem);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            alarmState = itemView.findViewById(R.id.switch_alarm_state);
            fabAddAlarm = itemView.findViewById(R.id.fab_add_alarm);
            removeAlarm = itemView.findViewById(R.id.fab_remove_alarm);
        }
    }
}
