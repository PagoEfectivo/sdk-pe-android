package com.example.pagoefectivo_sdk_android.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pagoefectivo_sdk_android.R;

import pe.elcomercio.pagoefectivosdk.cip.usermodel.CipEntity;

public class PaymentDetailActivity extends AppCompatActivity {

    private CipEntity cipEntity;
    private String agentName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_detail);
        init();
    }

    private void init() {

        //Get Intent Data
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(getString(R.string.agent_payment)) && intent.hasExtra(getString(R.string.serialize_cip))) {
            cipEntity = (CipEntity) getIntent().getSerializableExtra(getString(R.string.serialize_cip));
            agentName = getIntent().getStringExtra(getString(R.string.agent_payment));
        }

        //Init UI
        TextView lblTitleSummaryDetail = findViewById(R.id.lblTitleSummary);
        TextView lblNearAgent = findViewById(R.id.lblNearAgent);
        TextView lblCipCode = findViewById(R.id.lblCipCode);

        String titleSummary = String.format(getString(R.string.title_summary_payment), String.valueOf(cipEntity.getAmount()), agentName);
        String stepOneSummary = String.format(getString(R.string.step_one_for_payment), agentName);
        String stepThreeSummary = String.format(getString(R.string.step_three_for_payment), String.valueOf(cipEntity.getCip()));

        lblTitleSummaryDetail.setText(titleSummary);
        lblNearAgent.setText(stepOneSummary);
        lblCipCode.setText(stepThreeSummary);
    }
}