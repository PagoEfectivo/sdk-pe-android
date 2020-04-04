package pe.elcomercio.pagoefectivosdk.api;

public final class ApiHelper {

    private ApiHelper() {
        throw new IllegalAccessError("Helper class");
    }

    public static EndPointModel points() {
        return new EndPointModel();
    }

    static BaseModelPre basePre() {
        return new BaseModelPre();
    }

    static BaseModelPro basePro() {
        return new BaseModelPro();
    }

}
