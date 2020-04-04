package pe.elcomercio.pagoefectivosdk.cip;

import android.content.Context;

import java.util.List;

import pe.elcomercio.pagoefectivosdk.auth.AuthInteractor;
import pe.elcomercio.pagoefectivosdk.auth.AuthInteractorImpl;
import pe.elcomercio.pagoefectivosdk.cip.usermodel.CipEntity;
import pe.elcomercio.pagoefectivosdk.cip.usermodel.CipError;
import pe.elcomercio.pagoefectivosdk.cip.usermodel.CipRequest;
import pe.elcomercio.pagoefectivosdk.util.Language;
import pe.elcomercio.pagoefectivosdk.util.LogUtil;

public class SdkCipPresenterImpl extends SdkCipPresenterBase implements
        CipPresenter, SdkCipListener {

    private CipInteractor cipInteractor;
    private AuthInteractor authInteractor;

    private CipRequest cipRequest;
    private SdkCipListener sdkCipListener;

    private CipListener cipListener;
    private Context context;

    /**
     * Instantiates a new CipEntity presenter.
     */
    public SdkCipPresenterImpl() {

        if (cipInteractor == null) {
            cipInteractor = new CipInteractorImpl();
        }

        if (authInteractor == null) {
            authInteractor = new AuthInteractorImpl();
        }
    }

    @Override
    public void generateCipPostRequest(
            Language language,
            int serviceId,
            String accessKey,
            String secretKey,
            boolean isSandBox,
            CipRequest cipRequest,
            CipListener cipListener,
            Context context) {

        this.language = language;
        this.cipRequest = cipRequest;
        this.sdkCipListener = this;
        this.cipListener = cipListener;
        this.serviceId = serviceId;
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.isSandBox = isSandBox;
        this.context = context;

        if (tokenEntity.getToken().isEmpty()) {
            authInteractor.getToken(
                    language.toString(),
                    isSandBox,
                    getTokenRequest(),
                    this);
        } else {
            List<CipError> errorRequireds = CipHelper.validateGenerateCipRequestObject(
                    cipRequest, context);
            if (errorRequireds.isEmpty()) {
                cipInteractor.generateCip(
                        language,
                        tokenEntity.getToken(),
                        isSandBox,
                        CipHelper.mappingRequest(cipRequest),
                        this.sdkCipListener);
            } else {
                cipListener.onCipError(errorRequireds);
            }
        }
    }

    /**
     * Successful Method of {@link SdkCipListener} interface.
     *
     * @param cipEntity The cip response object when the web service generates a cip successful.
     */
    @Override
    public void onCipSuccessful(CipEntity cipEntity) {
        cipListener.onCipSuccessful(cipEntity);
    }

    /**
     * Error Method of {@link SdkCipListener} interface.
     *
     * @param message The error message if something was wrong with the web service.
     */
    @Override
    public void onCipError(List<CipError> message) {
        cipListener.onCipError(message);
    }

    /**
     * Method of {@link SdkCipListener} interface.
     *
     * @param message The failure message if something was wrong with the web service.
     */
    @Override
    public void onCipFailure(String message) {
        cipListener.onCipFailure(message);
    }

    /**
     * Refresh Token Method of {@link SdkCipListener} interface.
     * This method is called when the current token doesn't exist or has expired.
     */
    @Override
    public void onRefreshToken() {
        authInteractor.getToken(
                language.toString(),
                isSandBox,
                getTokenRequest(),
                this);
    }


    @Override
    public void onAuthSuccessful(String tps) {
        tokenEntity.setToken(tps);
        List<CipError> errorRequireds = CipHelper.validateGenerateCipRequestObject(
                cipRequest, context);
        if (errorRequireds.isEmpty()) {
            cipInteractor.generateCip(
                    language,
                    tokenEntity.getToken(),
                    isSandBox,
                    CipHelper.mappingRequest(cipRequest),
                    this.sdkCipListener);
        } else {
            cipListener.onCipError(errorRequireds);
        }
    }

    @Override
    public void onAuthError(List<CipError> errorList) {
        cipListener.onCipError(errorList);
    }

    @Override
    public void onAuthFailure(String failureMessage) {
        LogUtil.e("auth failure ", "auth failure ");
        cipListener.onCipFailure(failureMessage);
    }
}
