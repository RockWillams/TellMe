package com.taichina.xlt.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;

public class TimerPickFragment extends DialogFragment implements
        DatePickerDialog.OnDateSetListener {
    int year;
    int month;
    int day;
    DatePickerDialog mdate;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
         year = c.get(Calendar.YEAR);
         month = c.get(Calendar.MONTH);
         day = c.get(Calendar.DAY_OF_MONTH);
        Log.e("sss", "datadata");
        DatePickerDialog mdate=new DatePickerDialog(getActivity(), this, year, month, day);
        //mdate.onDateChanged(new )
        return mdate;
    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Log.d("OnDateSet", "select year:"+year+";month:"+month+";day:"+day);
    }

    public  String gettime(){
        return year+"年"+month+"月"+day+"日";
    }
}