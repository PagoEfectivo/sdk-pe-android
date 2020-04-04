package pe.elcomercio.pagoefectivosdk.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class DateTimeUtils {

    private DateTimeUtils() {
        throw new IllegalAccessError("DateTime Helper class");
    }

    public static String getDateTimeFormat() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault());
        return sdf.format(new Date()).replaceAll("(\\d{2})(\\d{2})$", "$1:$2");
    }
}
