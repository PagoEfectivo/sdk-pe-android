package pe.elcomercio.pagoefectivosdk.cip;

import pe.elcomercio.pagoefectivosdk.util.Language;
import pe.elcomercio.pagoefectivosdk.models.GenerateCipRequest;

interface CipInteractor {

    void generateCip(
            Language acceptLanguageHeader,
            String token,
            boolean isSandBox,
            GenerateCipRequest postCipRequest,
            SdkCipListener onSdkCipListener);
}
