package pe.elcomercio.pagoefectivosdk;

import pe.elcomercio.pagoefectivosdk.cip.CipListener;
import pe.elcomercio.pagoefectivosdk.cip.SdkCipPresenterImpl;
import pe.elcomercio.pagoefectivosdk.cip.usermodel.CipRequest;
import pe.elcomercio.pagoefectivosdk.util.Language;

import static pe.elcomercio.pagoefectivosdk.util.Helper.isAccessKeyValid;
import static pe.elcomercio.pagoefectivosdk.util.Helper.isSecretKeyValid;
import static pe.elcomercio.pagoefectivosdk.util.Helper.isServiceIdValid;

public class PagoEfectivoSdk extends PagoEfectivoApp {

    private SdkCipPresenterImpl sdkCipPresenter;

    private SdkCipPresenterImpl getSdkCipPresenter() {
        if (sdkCipPresenter == null) {
            sdkCipPresenter = new SdkCipPresenterImpl();
        }
        return sdkCipPresenter;
    }

    public void generateCip(Language language, CipRequest cipRequest, CipListener onCipListener) {
        if (validateKeyId()) {
            getSdkCipPresenter().generateCipPostRequest(
                    language,
                    serviceId,
                    accessKey,
                    secretKey,
                    isSandBox,
                    cipRequest,
                    onCipListener,
                    context);
        }
    }

    public void generateCip(CipRequest cipRequest, CipListener onCipListener) {
        if (validateKeyId()) {
            getSdkCipPresenter().generateCipPostRequest(
                    Language.SPANISH_PERU,
                    serviceId,
                    accessKey,
                    secretKey,
                    isSandBox,
                    cipRequest,
                    onCipListener,
                    context);
        }
    }

    @SuppressWarnings("SameReturnValue")
    private boolean validateKeyId() {
        if (!initialized) {
            throw new IllegalArgumentException("SDK Not initialized");
        }

        if (!isServiceIdValid(this.serviceId)) {
            throw new IllegalArgumentException("Incorrect serviceId");
        }

        if (!isAccessKeyValid(this.accessKey)) {
            throw new IllegalArgumentException("Incorrect accessKey");
        }

        if (!isSecretKeyValid(this.secretKey)) {
            throw new IllegalArgumentException("Incorrect secretKey");
        }

        return true;
    }

}
