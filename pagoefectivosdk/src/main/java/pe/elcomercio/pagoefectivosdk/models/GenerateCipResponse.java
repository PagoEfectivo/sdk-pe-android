package pe.elcomercio.pagoefectivosdk.models;

public class GenerateCipResponse {

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

        private int cip;
        private String currency;
        private double amount;
        private String transactionCode;
        private String dateExpiry;
        private String cipUrl;

        public int getCip() {
            return cip;
        }

        public void setCip(int cip) {
            this.cip = cip;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public String getTransactionCode() {
            return transactionCode;
        }

        public void setTransactionCode(String transactionCode) {
            this.transactionCode = transactionCode;
        }

        public String getDateExpiry() {
            return dateExpiry;
        }

        public void setDateExpiry(String dateExpiry) {
            this.dateExpiry = dateExpiry;
        }

        public String getCipUrl() {
            return cipUrl;
        }

        public void setCipUrl(String cipUrl) {
            this.cipUrl = cipUrl;
        }
    }
}
