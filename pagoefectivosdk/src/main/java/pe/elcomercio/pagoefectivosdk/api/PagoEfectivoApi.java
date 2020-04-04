package pe.elcomercio.pagoefectivosdk.api;

import pe.elcomercio.pagoefectivosdk.models.GenerateCipRequest;
import pe.elcomercio.pagoefectivosdk.models.GenerateCipResponse;
import pe.elcomercio.pagoefectivosdk.models.GetTokenRequest;
import pe.elcomercio.pagoefectivosdk.models.GetTokenResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;

import static pe.elcomercio.pagoefectivosdk.util.HeadersConstant.HEADER_ACCEPT_LANGUAGE;
import static pe.elcomercio.pagoefectivosdk.util.HeadersConstant.HEADER_AUTHORIZATION;
import static pe.elcomercio.pagoefectivosdk.util.HeadersConstant.ORIGIN;

public interface PagoEfectivoApi {

    @POST
    Call<GetTokenResponse> getToken(
            @Header(HEADER_ACCEPT_LANGUAGE) String language,
            @Url String url,
            @Body GetTokenRequest GetTokenRequest);

    @Headers("Content-Type: application/json")
    @POST
    Call<GenerateCipResponse> generateCip(
            @Header(HEADER_AUTHORIZATION) String authorization,
            @Header(HEADER_ACCEPT_LANGUAGE) String language,
            @Header(ORIGIN) String origin,
            @Url String url,
            @Body GenerateCipRequest cipRequest);

}
