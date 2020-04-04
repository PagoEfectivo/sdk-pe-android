package pe.elcomercio.pagoefectivosdk.api;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class PagoEfectivoApiManager {

    private static volatile PagoEfectivoApi pagoEfectivoApi;

    private PagoEfectivoApiManager() {
        if (pagoEfectivoApi != null) {
            throw new RuntimeException(
                    "Use apiManager() method to get the single instance of this class.");
        }
    }

    public static PagoEfectivoApi apiManager(boolean isSandBox) {

        PagoEfectivoApi result = pagoEfectivoApi;

        if (result == null) {
            synchronized (PagoEfectivoApi.class) {
                result = pagoEfectivoApi;
                if (result == null) {
                    OkHttpClient.Builder builder = new OkHttpClient.Builder();

                    builder.addInterceptor(getInterceptor(isSandBox))
                            .writeTimeout(10, TimeUnit.SECONDS)
                            .connectTimeout(10, TimeUnit.SECONDS)
                            .readTimeout(10, TimeUnit.SECONDS);

                    OkHttpClient okHttpClient = builder.build();

                    Retrofit client = new Retrofit.Builder()
                            .baseUrl(getBaseUrl(isSandBox))
                            .client(okHttpClient)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    pagoEfectivoApi = result = client.create(PagoEfectivoApi.class);
                }
            }
        }
        return result;
    }

    static Retrofit getRetrofit(boolean isSandBox) {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(getInterceptor(isSandBox))
                .build();

        return new Retrofit.Builder()
                .baseUrl(getBaseUrl(isSandBox))
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private static HttpLoggingInterceptor getInterceptor(boolean isSandBox) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

        if (isSandBox) {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            logging.setLevel(HttpLoggingInterceptor.Level.NONE);
        }

        return logging;
    }

    private static String getBaseUrl(boolean isSandBox) {

        if (isSandBox) {
            Log.i("PagoEfectivoSDK", ApiHelper.basePre().getSanBox());
            return ApiHelper.basePre().getURLBASE();
        } else {
            return ApiHelper.basePro().getURLBASE();
        }
    }
}
