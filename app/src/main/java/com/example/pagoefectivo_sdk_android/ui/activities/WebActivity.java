package com.example.pagoefectivo_sdk_android.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pagoefectivo_sdk_android.R;

import pe.elcomercio.pagoefectivosdk.cip.usermodel.CipEntity;

public class WebActivity extends AppCompatActivity {

    private CipEntity cipEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        init();
    }

    private void init() {
        WebView myWebView = findViewById(R.id.wvBrowser);

        //Get Intent Data
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(getString(R.string.serialize_cip))) {
            cipEntity = (CipEntity) getIntent().getSerializableExtra(getString(R.string.serialize_cip));
        }

        myWebView.loadUrl(cipEntity.getCipUrl());
    }
}
