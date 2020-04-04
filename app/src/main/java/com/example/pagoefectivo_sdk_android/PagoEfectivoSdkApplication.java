package com.example.pagoefectivo_sdk_android;

import android.app.Application;

import pe.elcomercio.pagoefectivosdk.PagoEfectivoSdk;

public class PagoEfectivoSdkApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        PagoEfectivoSdk pagoEfectivoSdk = PagoEfectivoSdk.initialize(this);
        //TODO RIGHT HERE SETUP YOUR CREDENTIALS
        pagoEfectivoSdk.setServiceId(0);
        pagoEfectivoSdk.setAccessKey("YOUR_ACCESS_KEY");
        pagoEfectivoSdk.setSecretKey("YOUR_SECRET_KEY");
        pagoEfectivoSdk.setSandBox(true);
    }
}
