package com.sagar.android.officetimer;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;
import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    String createDate;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //current time as the default value for the picker
        final Calendar calendar = Calendar.getInstance();
        int date = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        //create a new instance of TimePickerDialog
        return new DatePickerDialog(getActivity(), this, year, month, date);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        this.createDate = String.valueOf(dayOfMonth) + "/" + String.valueOf(month+1) + "/"
                + String.valueOf(year);
        TextView dateTextView = getActivity().findViewById(R.id.selectedDate_textview);
        dateTextView.setText(createDate);
    }
}