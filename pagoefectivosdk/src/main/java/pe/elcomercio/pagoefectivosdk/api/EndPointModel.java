package pe.elcomercio.pagoefectivosdk.api;

import pe.elcomercio.pagoefectivosdk.BuildConfig;

@SuppressWarnings("SameReturnValue")
public class EndPointModel {

    public String getAUTHORIZATIONS() {
        return BuildConfig.AUTHORIZATIONS;
    }

    public String getCIPS() {
        return BuildConfig.CIPS;
    }
}
