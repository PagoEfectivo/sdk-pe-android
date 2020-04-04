package com.example.pagoefectivo_sdk_android.ui.components;

import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.DialogFragment;

import com.example.pagoefectivo_sdk_android.R;

public class DatePickerDialogFragment extends DialogFragment implements View.OnClickListener{

    private OnDateSetListener mListener;

    private DatePicker datePicker;

    public static DatePickerDialogFragment newInstance(){
        return new DatePickerDialogFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context != null) {
            mListener = (OnDateSetListener) context;
        }
    }

    @Override
    public void onDetach() {
        this.mListener = null;
        super.onDetach();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dialog_date_picker, container, false);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        datePicker = view.findViewById(R.id.datePicker);
        AppCompatButton btnCancel = view.findViewById(R.id.btnCancel);
        AppCompatButton btnOk = view.findViewById(R.id.btnOk);

        assert btnOk != null;
        assert btnCancel != null;
        btnOk.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnOk){
            getDialog().dismiss();
            mListener.onDateSet(datePicker,
                    datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
        }else if(v.getId() == R.id.btnCancel){
            getDialog().dismiss();
        }
    }
}