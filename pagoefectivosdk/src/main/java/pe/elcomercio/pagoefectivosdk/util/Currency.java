package pe.elcomercio.pagoefectivosdk.util;

public enum Currency {

    PEN("PEN"),
    USD("USD");

    private final String currency;

    Currency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return currency;
    }
}
