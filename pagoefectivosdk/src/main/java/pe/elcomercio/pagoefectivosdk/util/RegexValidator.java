package pe.elcomercio.pagoefectivosdk.util;

import java.util.regex.Pattern;

public final class RegexValidator {

    private static final Pattern ALPHANUMERIC = Pattern.compile("^[a-zA-Z0-9]+$");

    private RegexValidator() {
        throw new IllegalAccessError("RegexValidator Helper class");
    }

    /**
     * Validate if a string is alphanumeric
     * You only need to pass {@String string}.
     *
     * @param name The string name to be validated.
     *
     * @return boolean value, true if is an alphanumeric string  or false if not.
     */
    public static boolean isAlphaNumericString(String name) {
        return ALPHANUMERIC.matcher(name).matches();
    }

}
