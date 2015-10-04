package com.sample.gpd.myfavoritewine.ui.widget.custom;

import android.annotation.TargetApi;
import android.app.Dialog;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by kauesantoja on 03/10/2015.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class DatePickerDialog extends DialogFragment implements android.app.DatePickerDialog.OnDateSetListener, android.app.DatePickerDialog.OnDismissListener{

    private android.app.DatePickerDialog.OnDateSetListener mCallbackDataSet;
    private android.app.DatePickerDialog.OnDismissListener mCallbackOnDismiss;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH)+1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return new android.app.DatePickerDialog(getActivity(),this,year,month,day);
    }

    public void setOnDateSetListener(android.app.DatePickerDialog.OnDateSetListener callback){
        mCallbackDataSet = callback;
    }

    public void setOnDismissListener(android.app.DatePickerDialog.OnDismissListener callback){
        mCallbackOnDismiss = callback;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        if(mCallbackDataSet != null){
            mCallbackDataSet.onDateSet(datePicker, year, month, dayOfMonth);
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        if(mCallbackOnDismiss != null){
            mCallbackOnDismiss.onDismiss(dialog);
        }
    }
}
