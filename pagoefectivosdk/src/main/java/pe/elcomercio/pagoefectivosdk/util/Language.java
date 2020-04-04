package pe.elcomercio.pagoefectivosdk.util;

public enum Language {

    ENGLISH_USA("en", "US"),
    SPANISH_PERU("es", "PE");

    private final String language;
    private final String country;

    Language(String language, String country) {
        this.language = language;
        this.country = country;
    }

    @Override
    public String toString() {
        return language + "-" + country;
    }
}
