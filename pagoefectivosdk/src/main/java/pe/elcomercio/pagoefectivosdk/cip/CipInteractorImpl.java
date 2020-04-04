package pe.elcomercio.pagoefectivosdk.cip;

import java.util.ArrayList;
import java.util.List;

import pe.elcomercio.pagoefectivosdk.api.ApiHelper;
import pe.elcomercio.pagoefectivosdk.api.ErrorUtil;
import pe.elcomercio.pagoefectivosdk.api.PagoEfectivoApiManager;
import pe.elcomercio.pagoefectivosdk.cip.usermodel.CipEntity;
import pe.elcomercio.pagoefectivosdk.cip.usermodel.CipError;
import pe.elcomercio.pagoefectivosdk.util.Language;
import pe.elcomercio.pagoefectivosdk.models.ErrorResponse;
import pe.elcomercio.pagoefectivosdk.models.GenerateCipRequest;
import pe.elcomercio.pagoefectivosdk.models.GenerateCipResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static pe.elcomercio.pagoefectivosdk.util.Constant.ERROR_503;
import static pe.elcomercio.pagoefectivosdk.util.Constant.TYPE_AUTH_HEADER;
import static pe.elcomercio.pagoefectivosdk.util.Constant.TYPE_DEVICE;

class CipInteractorImpl implements CipInteractor {

    @Override
    public void generateCip(
            Language acceptLanguageHeader,
            String token,
            final boolean isSandBox,
            GenerateCipRequest postCipRequest,
            final SdkCipListener onSdkCipListener) {

        Call<GenerateCipResponse> call = PagoEfectivoApiManager.apiManager(isSandBox).generateCip(
                TYPE_AUTH_HEADER + token,
                acceptLanguageHeader.toString(),
                TYPE_DEVICE,
                ApiHelper.points().getCIPS(),
                postCipRequest);

        call.enqueue(new Callback<GenerateCipResponse>() {
            @Override
            public void onResponse(Call<GenerateCipResponse> call, Response<GenerateCipResponse> response) {

                if (response.isSuccessful()) {
                    GenerateCipResponse.DataBean cipResponse = response.body().getData();

                    CipEntity cipEntity = new CipEntity(
                            cipResponse.getCip(),
                            cipResponse.getTransactionCode(),
                            cipResponse.getDateExpiry(),
                            cipResponse.getCurrency(),
                            cipResponse.getAmount(),
                            cipResponse.getCipUrl());

                    onSdkCipListener.onCipSuccessful(cipEntity);
                } else if (response.code() == 401) {
                    onSdkCipListener.onRefreshToken();
                } else if (response.code() == 503) {
                    onSdkCipListener.onCipFailure(ERROR_503);
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
                        onSdkCipListener.onCipError(listErrors);
                    }
                }
            }

            @Override
            public void onFailure(Call<GenerateCipResponse> call, Throwable t) {
                onSdkCipListener.onCipFailure(t.getMessage());
            }
        });
    }
}
