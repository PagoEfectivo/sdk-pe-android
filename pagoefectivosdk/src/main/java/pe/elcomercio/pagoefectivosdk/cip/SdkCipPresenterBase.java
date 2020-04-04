package pe.elcomercio.pagoefectivosdk.cip;

import pe.elcomercio.pagoefectivosdk.auth.AuthInteractor;
import pe.elcomercio.pagoefectivosdk.auth.model.TokenEntity;
import pe.elcomercio.pagoefectivosdk.util.DateTimeUtils;
import pe.elcomercio.pagoefectivosdk.util.Language;
import pe.elcomercio.pagoefectivosdk.models.GetTokenRequest;

import static pe.elcomercio.pagoefectivosdk.util.Helper.concatenateAuthRequest;
import static pe.elcomercio.pagoefectivosdk.util.Helper.digest;

abstract class SdkCipPresenterBase implements AuthInteractor.OnAuthListener {

    protected int serviceId;
    String accessKey;
    String secretKey;
    protected boolean isSandBox;
    protected Language language;

    final TokenEntity tokenEntity = TokenEntity.tokenInstance();

    GetTokenRequest getTokenRequest() {
        String dateRequest = DateTimeUtils.getDateTimeFormat();
        String hashString = digest(
                CipHelper.getSecure(),
                concatenateAuthRequest(serviceId, accessKey, secretKey, dateRequest));

        GetTokenRequest GetTokenRequest = new GetTokenRequest();
        GetTokenRequest.setIdService(serviceId);
        GetTokenRequest.setAccessKey(accessKey);
        GetTokenRequest.setDateRequest(dateRequest);
        GetTokenRequest.setHashString(hashString);

        return GetTokenRequest;
    }
}
