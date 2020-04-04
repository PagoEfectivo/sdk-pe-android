package pe.elcomercio.pagoefectivosdk.models;

public class GetTokenResponse {

    private String code;
    private String message;
    private DataBean data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {

        private String token;
        private String codeService;
        private String tokenStart;
        private String tokenExpires;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getCodeService() {
            return codeService;
        }

        public void setCodeService(String codeService) {
            this.codeService = codeService;
        }

        public String getTokenStart() {
            return tokenStart;
        }

        public void setTokenStart(String tokenStart) {
            this.tokenStart = tokenStart;
        }

        public String getTokenExpires() {
            return tokenExpires;
        }

        public void setTokenExpires(String tokenExpires) {
            this.tokenExpires = tokenExpires;
        }
    }
}
