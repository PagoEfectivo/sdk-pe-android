package com.example.pagoefectivo_sdk_android.ui.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pagoefectivo_sdk_android.R;
import com.example.pagoefectivo_sdk_android.adapter.WhereToPayAdapter;

import java.util.ArrayList;
import java.util.List;

import pe.elcomercio.pagoefectivosdk.cip.usermodel.CipEntity;

import static com.example.pagoefectivo_sdk_android.util.Constant.PAGO_EFECTIVO_AGENTES;

public class WhereToPayActivity extends AppCompatActivity {

    private static final int TYPE_WHERE_PAY = 1;
    private CipEntity cipEntity;
    private int typePayment;
    private final List<String> typePaymentMethods = new ArrayList<>();
    private final List<Object> listMethodPaymentsAndCip = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_where_to_pay);
        init();
    }

    private void init() {

        //Type Payments
        typePaymentMethods.add(getString(R.string.title_movil));
        typePaymentMethods.add(getString(R.string.title_agents));

        //Get CipEntity
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(getString(R.string.type_method_payment)) && intent.hasExtra(getString(R.string.serialize_cip))) {
            cipEntity = (CipEntity) getIntent().getSerializableExtra(getString(R.string.serialize_cip));
            typePayment = getIntent().getIntExtra(getString(R.string.type_method_payment), 0);
            prepareData();
        }

        //Set title toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(typePaymentMethods.get(typePayment));
        }

        //Setup Adapter
        WhereToPayAdapter whereToPayAdapter = new WhereToPayAdapter(listMethodPaymentsAndCip, this);
        whereToPayAdapter.setOnItemClickListener(new WhereToPayAdapter.OnItemClickListener() {
            @Override
            public void onItemClickAgentMobile(int position, int typeOfHolder) {



                if (typeOfHolder == TYPE_WHERE_PAY) {
                    String agentName = (String) listMethodPaymentsAndCip.get(position);
                    Intent intent = new Intent(WhereToPayActivity.this, PaymentDetailActivity.class);
                    intent.putExtra(getString(R.string.serialize_cip), cipEntity);
                    intent.putExtra(getString(R.string.agent_payment), agentName);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(WhereToPayActivity.this, WebActivity.class);
                    intent.putExtra(getString(R.string.serialize_cip), cipEntity);
                    startActivity(intent);
                }

            }
        });

        //Setup Recycler
        RecyclerView rcvWhereToPay = findViewById(R.id.rcvWhereToPay);
        rcvWhereToPay.setAdapter(whereToPayAdapter);
        rcvWhereToPay.setHasFixedSize(true);
        rcvWhereToPay.addItemDecoration(new DividerItemDecoration(rcvWhereToPay.getContext(), DividerItemDecoration.VERTICAL));
    }

    private void prepareData() {

        listMethodPaymentsAndCip.add(cipEntity);

        listMethodPaymentsAndCip.add(getString(R.string.agent_bcp));
        listMethodPaymentsAndCip.add(getString(R.string.agent_banbif));
        listMethodPaymentsAndCip.add(getString(R.string.agent_bbva));
        listMethodPaymentsAndCip.add(getString(R.string.agent_scotiabank));
        listMethodPaymentsAndCip.add(getString(R.string.agent_ibk));

        if (typePayment == PAGO_EFECTIVO_AGENTES) {
            listMethodPaymentsAndCip.add(getString(R.string.agent_kasnet));
            listMethodPaymentsAndCip.add(getString(R.string.agent_wester));
            listMethodPaymentsAndCip.add(getString(R.string.agent_full_charge));
        }
    }
}
