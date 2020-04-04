package pe.elcomercio.pagoefectivosdk.api;

import pe.elcomercio.pagoefectivosdk.BuildConfig;

@SuppressWarnings("SameReturnValue")
class BaseModelPre {

    String getURLBASE() {
        return BuildConfig.URL_BASE_PRE;
    }

    String getSanBox() {
        return "SANDBOX";
    }
}
