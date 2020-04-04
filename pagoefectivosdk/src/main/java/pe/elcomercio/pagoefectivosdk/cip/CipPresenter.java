package pe.elcomercio.pagoefectivosdk.cip;

import android.content.Context;

import pe.elcomercio.pagoefectivosdk.cip.usermodel.CipRequest;
import pe.elcomercio.pagoefectivosdk.util.Language;

interface CipPresenter {

    void generateCipPostRequest(
            final Language language,
            int serviceId,
            String accessKey,
            String secretKey,
            boolean isSandBox,
            final CipRequest cipRequest,
            final CipListener cipListener,
            final Context context);

}
