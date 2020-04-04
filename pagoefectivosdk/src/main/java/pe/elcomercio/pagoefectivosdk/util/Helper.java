package pe.elcomercio.pagoefectivosdk.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pe.elcomercio.pagoefectivosdk.cip.SecureModel;

public final class Helper {

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile(
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private Helper() {
        throw new IllegalAccessError("Helper class");
    }

    public static boolean isServiceIdValid(int idService) {
        return idService > 0;
    }

    public static boolean isAccessKeyValid(String accessKey) {
        return !(accessKey == null ||
                accessKey.isEmpty() ||
                accessKey.length() != 20 ||
                !RegexValidator.isAlphaNumericString(accessKey));
    }

    public static boolean isSecretKeyValid(String secretKey) {
        return !(secretKey == null || secretKey.isEmpty() || secretKey.length() != 40);
    }

    public static String concatenateAuthRequest(
            int serviceId,
            String accessKey,
            String secretKey,
            String dateRequest) {
        return serviceId + "." + accessKey + "." + secretKey + "." + dateRequest;
    }

    public static boolean isIdServiceValid(int idService) {
        return idService > 0;
    }

    public static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public static int numberOfDecimal(double value) {
        String text = Double.toString(Math.abs(value));
        int integerPlaces = text.indexOf('.');
        return text.length() - integerPlaces - 1;
    }

    public static boolean isDecimal(String cad) {
        boolean existPoint = false;
        StringBuilder intPart = new StringBuilder();
        StringBuilder floatPart = new StringBuilder();

        int i, positionsOfPoint;

        //Detectar si hay un punto decimal en la cadena
        for (i = 0; i < cad.length(); i++) {
            if (cad.charAt(i) == '.') {
                existPoint = true;
            }
        }

        //Si hay punto guardar la posicion donde se encuentra el carater punto
        if (existPoint) {
            //(si la cadena tiene varios puntos, detecta donde esta el primero).
            positionsOfPoint = cad.indexOf('.');
        } else {
            //Si no hay punto; no es decimal
            return false;
        }

        //Si el punto esta al final o al principio no es un decimal
        if (positionsOfPoint == cad.length() - 1 || positionsOfPoint == 0) {
            return false;
        }

        //Guardar la parte entera en una variable
        for (i = 0; i < positionsOfPoint; i++) {
            intPart.append(cad.charAt(i));
        }

        //Si alguno de los caracteres de la parte entera no son digitos no es decimal
        for (i = 0; i < intPart.length(); i++) {
            if (!Character.isDigit(intPart.charAt(i))) {
                return false;
            }
        }

        //Guardar la parte decimal en una variable
        for (i = positionsOfPoint + 1; i < cad.length(); i++) {
            floatPart.append(cad.charAt(i));
        }

        //Si alguno de los caracteres de la parte decimal no es un digito no es decimal.
        // Incluye el caso en el que la cadena tenga dos o mas puntos.
        for (i = 0; i < floatPart.length(); i++) {
            if (!Character.isDigit(floatPart.charAt(i))) {
                return false;
            }
        }

        //Si paso todas las pruebas anteriores, la cadena es un Numero decimal
        return true;
    }

    public static Calendar convertTimestampToCalendar(String timestamp) {
        try {
            //Example "2017-08-28T11:46:01-05:00";
            SimpleDateFormat f = new SimpleDateFormat(Constant.DATE_PE_FORMAT, Locale.getDefault());
            Date d = f.parse(timestamp);

            Calendar cal = Calendar.getInstance();
            cal.setTime(d);

            return cal;
        } catch (ParseException e) {
            return null;
        }
    }

    public static String dateToString(Date date) {
        DateFormat df = new SimpleDateFormat(Constant.DATE_PE_FORMAT, Locale.getDefault());
        return df.format(date);
    }

    public static boolean isValidDateFormat(String dateString, String format) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
            date = sdf.parse(dateString);
            if (!dateString.equals(sdf.format(date))) {
                date = null;
            }
        } catch (ParseException ex) {
            LogUtil.e("Helper", ex.getMessage());
        }

        return date != null;
    }

    private static String encodeHex(byte[] digest) {
        StringBuilder sb = new StringBuilder();
        for (byte aDigest : digest) {
            sb.append(Integer.toString((aDigest & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    public static String digest(SecureModel alg, String input) {
        try {
            MessageDigest md = MessageDigest.getInstance(alg.getName());
            byte[] buffer = input.getBytes("UTF-8");
            md.update(buffer);
            byte[] digest = md.digest();
            return encodeHex(digest);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            LogUtil.e("Helper", e.getMessage());
            return e.getMessage();
        }
    }
}
