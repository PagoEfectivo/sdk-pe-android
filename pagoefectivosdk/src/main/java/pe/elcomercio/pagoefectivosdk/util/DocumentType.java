package pe.elcomercio.pagoefectivosdk.util;

public enum DocumentType {
    DNI("DNI"),
    PASSPORT("PAS"),
    LIBRETA_MILITAR("LMI"),
    PARTIDA_NACIMIENTO("PAR"),
    OTROS("NAN");

    private final String documentType;

    DocumentType(String documentType) {
        this.documentType = documentType;
    }

    @Override
    public String toString() {
        return documentType;
    }
}
