package com.example.pagoefectivo_sdk_android.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pagoefectivo_sdk_android.R;
import com.example.pagoefectivo_sdk_android.adapter.PaymentMethodAdapter;
import com.example.pagoefectivo_sdk_android.model.PaymentMethodEntity;

import java.util.ArrayList;
import java.util.List;

import pe.elcomercio.pagoefectivosdk.cip.usermodel.CipEntity;

import static com.example.pagoefectivo_sdk_android.util.Constant.PAGO_EFECTIVO_AGENTES;
import static com.example.pagoefectivo_sdk_android.util.Constant.PAGO_EFECTIVO_MOVIL;
import static com.example.pagoefectivo_sdk_android.util.Constant.PAGO_OTROS;

public class PaymentMethodActivity extends AppCompatActivity {

    private final List<PaymentMethodEntity> paymentMethodList = new ArrayList<>();
    private CipEntity cipEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);
        init();
    }

    private void init() {
        //Get CipEntity
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(getString(R.string.serialize_cip))) {
            cipEntity = (CipEntity) intent.getSerializableExtra(getString(R.string.serialize_cip));
        }

        //Init Adapter
        paymentMethodList.add(new PaymentMethodEntity(PAGO_EFECTIVO_MOVIL, getString(R.string.pe_mobile_internet)));
        paymentMethodList.add(new PaymentMethodEntity(PAGO_EFECTIVO_AGENTES, getString(R.string.pe_agents_agency)));
        paymentMethodList.add(new PaymentMethodEntity(PAGO_OTROS, getString(R.string.pe_visa)));
        paymentMethodList.add(new PaymentMethodEntity(PAGO_OTROS, getString(R.string.pe_master_card)));
        paymentMethodList.add(new PaymentMethodEntity(PAGO_OTROS, getString(R.string.pe_american_express)));
        paymentMethodList.add(new PaymentMethodEntity(PAGO_OTROS, getString(R.string.pe_dinners_club)));

        PaymentMethodAdapter paymentMethodAdapter = new PaymentMethodAdapter(paymentMethodList);
        paymentMethodAdapter.setOnItemClickListener(new PaymentMethodAdapter.OnItemClickListener() {
            @Override
            public void onItemClickMethodPayment(int typeItem) {
                whereToPay(typeItem);
            }
        });

        //Setup Recycler
        RecyclerView rcvPaymentMethods = findViewById(R.id.rcvPaymentMethods);
        rcvPaymentMethods.setAdapter(paymentMethodAdapter);
        rcvPaymentMethods.setHasFixedSize(true);
        rcvPaymentMethods.addItemDecoration(new DividerItemDecoration(rcvPaymentMethods.getContext(), DividerItemDecoration.VERTICAL));
    }

    private void whereToPay(int typeItem) {
        switch (typeItem) {
            case PAGO_EFECTIVO_MOVIL:
            case PAGO_EFECTIVO_AGENTES:
                Intent intent = new Intent(this, WhereToPayActivity.class);
                intent.putExtra(getString(R.string.serialize_cip), cipEntity);
                intent.putExtra(getString(R.string.type_method_payment), typeItem);
                startActivity(intent);
                break;
            default:
                Toast.makeText(this, R.string.payment_method_not_available, Toast.LENGTH_SHORT).show();
        }
    }
}
