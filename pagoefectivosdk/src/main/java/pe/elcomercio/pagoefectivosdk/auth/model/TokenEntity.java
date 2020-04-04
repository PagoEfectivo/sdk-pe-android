package pe.elcomercio.pagoefectivosdk.auth.model;

public final class TokenEntity {

    private String token = "";

    private static volatile TokenEntity instance;

    private TokenEntity() {
        if (instance != null) {
            throw new RuntimeException(
                    "Use tokenInstance() method to get the single instance of this class.");
        }
    }

    public static TokenEntity tokenInstance() {
        TokenEntity result = instance;
        if (result == null) {
            synchronized (TokenEntity.class) {
                result = instance;
                if (result == null) {
                    instance = result = new TokenEntity();
                }
            }
        }

        return result;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
