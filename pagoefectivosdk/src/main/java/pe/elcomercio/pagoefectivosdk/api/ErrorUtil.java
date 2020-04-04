package pe.elcomercio.pagoefectivosdk.api;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import pe.elcomercio.pagoefectivosdk.models.ErrorResponse;
import retrofit2.Converter;
import retrofit2.Response;

public final class ErrorUtil {

    private ErrorUtil() {
        throw new IllegalAccessError("Error util class");
    }

    public static ErrorResponse parseError(Response<?> response, boolean isSandBox) {

        Converter<ResponseBody, ErrorResponse> converter = PagoEfectivoApiManager
                .getRetrofit(isSandBox).responseBodyConverter(ErrorResponse.class, new Annotation[0]);

        ErrorResponse error;

        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new ErrorResponse();
        }

        return error;
    }
}
