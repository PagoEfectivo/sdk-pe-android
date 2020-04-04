package pe.elcomercio.pagoefectivosdk.auth;

import java.util.ArrayList;
import java.util.List;

import pe.elcomercio.pagoefectivosdk.api.ApiHelper;
import pe.elcomercio.pagoefectivosdk.api.ErrorUtil;
import pe.elcomercio.pagoefectivosdk.api.PagoEfectivoApiManager;
import pe.elcomercio.pagoefectivosdk.cip.usermodel.CipError;
import pe.elcomercio.pagoefectivosdk.models.ErrorResponse;
import pe.elcomercio.pagoefectivosdk.models.GetTokenRequest;
import pe.elcomercio.pagoefectivosdk.models.GetTokenResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static pe.elcomercio.pagoefectivosdk.util.Constant.ERROR_503;

public class AuthInteractorImpl implements AuthInteractor {

    @Override
    public void getToken(
            String acceptLanguageHeader,
            final boolean isSandBox,
            GetTokenRequest GetTokenRequest,
            final OnAuthListener onAuthListener) {

        Call<GetTokenResponse> call = PagoEfectivoApiManager.apiManager(isSandBox)
                .getToken(acceptLanguageHeader, ApiHelper.points().getAUTHORIZATIONS(), GetTokenRequest);

        call.enqueue(new Callback<GetTokenResponse>() {
            @Override
            public void onResponse(Call<GetTokenResponse> call, Response<GetTokenResponse> response) {
                if (response.isSuccessful()) {
                    onAuthListener.onAuthSuccessful(response.body().getData().getToken());
                } else if (response.code() == 503) {
                    onAuthListener.onAuthFailure(ERROR_503);
                } else {
                    List<ErrorResponse.DataBean> listError =
                            ErrorUtil.parseError(response, isSandBox).getData();
                    List<CipError> listErrors = new ArrayList<>();

                    if (listError != null) {
                        for (ErrorResponse.DataBean error : listError) {
                            listErrors.add(new CipError(
                                    error.getCode(),
                                    error.getField(),
                                    error.getMessage()));
                        }
                        onAuthListener.onAuthError(listErrors);
                    }

                }
            }

            @Override
            public void onFailure(Call<GetTokenResponse> call, Throwable t) {
                onAuthListener.onAuthFailure(t.getMessage());
            }
        });
    }
}
