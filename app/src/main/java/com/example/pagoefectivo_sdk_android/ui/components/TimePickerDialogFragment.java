package com.example.pagoefectivo_sdk_android.ui.components;

import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.DialogFragment;

import com.example.pagoefectivo_sdk_android.R;

public class TimePickerDialogFragment extends DialogFragment implements View.OnClickListener {

    private TimePickerDialog.OnTimeSetListener mListener;

    private TimePicker timePicker;

    public static TimePickerDialogFragment newInstance() {
        return new TimePickerDialogFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context != null) {
            this.mListener = (TimePickerDialog.OnTimeSetListener) context;
        }
    }

    @Override
    public void onDetach() {
        this.mListener = null;
        super.onDetach();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dialog_time_picker, container, false);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        timePicker = view.findViewById(R.id.timePicker);
        AppCompatButton btnCancel = view.findViewById(R.id.btnCancel);
        AppCompatButton btnOk = view.findViewById(R.id.btnOk);

        assert btnOk != null;
        assert btnCancel != null;
        btnOk.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        timePicker.setIs24HourView(false);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnOk) {
            getDialog().dismiss();
            mListener.onTimeSet(timePicker,
                    timePicker.getCurrentHour(), timePicker.getCurrentMinute());
        } else if (v.getId() == R.id.btnCancel) {
            getDialog().dismiss();
        }
    }
}
