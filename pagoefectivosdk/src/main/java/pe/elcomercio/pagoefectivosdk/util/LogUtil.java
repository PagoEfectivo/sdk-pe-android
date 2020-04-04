package pe.elcomercio.pagoefectivosdk.util;

import android.util.Log;

import pe.elcomercio.pagoefectivosdk.BuildConfig;

public final class LogUtil {

    private LogUtil() {
        throw new IllegalAccessError("LogUtil Helper class");
    }

    public static void e(String tag, String message) {
        if (BuildConfig.DEBUG) {
            Log.e("z-" + tag, message);
        }
    }

    public static void d(String tag, String message) {
        if (BuildConfig.DEBUG) {
            Log.d("z-" + tag, message);
        }
    }

    public static void i(String tag, String message) {
        if (BuildConfig.DEBUG) {
            Log.i("z-" + tag, message);
        }
    }

    public static void w(String tag, String message) {
        if (BuildConfig.DEBUG) {
            Log.w("z-" + tag, message);
        }
    }
}
