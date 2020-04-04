package pe.elcomercio.pagoefectivosdk.auth;

import java.util.List;

import pe.elcomercio.pagoefectivosdk.cip.usermodel.CipError;
import pe.elcomercio.pagoefectivosdk.models.GetTokenRequest;

public interface AuthInteractor {

    /**
     * The interface for error or success events in auth.
     */
    interface OnAuthListener {

        /**
         * On auth successful.
         *
         * @param tps the GetTokenResponse
         */
        void onAuthSuccessful(String tps);

        /**
         * On auth error.
         *
         * @param errorMessage the error message
         */
        void onAuthError(List<CipError> errorMessage);

        /**
         * On auth failure.
         *
         * @param failureMessage the failure message
         */
        void onAuthFailure(String failureMessage);
    }

    /**
     * Gets token interface.
     *
     * @param languageHeaderString the language header string
     * @param isSandBox            if the SDK is with SandBox activate
     * @param GetTokenRequest                  the Object GetTokenResponse
     * @param onAuthListener       the on auth listener
     */
    void getToken(
            String languageHeaderString,
            boolean isSandBox,
            GetTokenRequest GetTokenRequest,
            OnAuthListener onAuthListener);
}
