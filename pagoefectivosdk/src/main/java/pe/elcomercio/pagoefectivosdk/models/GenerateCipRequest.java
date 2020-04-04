package pe.elcomercio.pagoefectivosdk.models;

public class GenerateCipRequest {

    private String currency;
    private double amount;
    private String transactionCode;
    private String adminEmail;
    private String dateExpiry;
    private String paymentConcept;
    private String additionalData;
    private String userEmail;
    private String userName;
    private String userLastName;
    private String userUbigeo;
    private String userCountry;
    private String userDocumentType;
    private String userDocumentNumber;
    private String userPhone;
    private String userCodeCountry;

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

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getDateExpiry() {
        return dateExpiry;
    }

    public void setDateExpiry(String dateExpiry) {
        this.dateExpiry = dateExpiry;
    }

    public String getPaymentConcept() {
        return paymentConcept;
    }

    public void setPaymentConcept(String paymentConcept) {
        this.paymentConcept = paymentConcept;
    }

    public String getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(String additionalData) {
        this.additionalData = additionalData;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserUbigeo() {
        return userUbigeo;
    }

    public void setUserUbigeo(String userUbigeo) {
        this.userUbigeo = userUbigeo;
    }

    public String getUserCountry() {
        return userCountry;
    }

    public void setUserCountry(String userCountry) {
        this.userCountry = userCountry;
    }

    public String getUserDocumentType() {
        return userDocumentType;
    }

    public void setUserDocumentType(String userDocumentType) {
        this.userDocumentType = userDocumentType;
    }

    public String getUserDocumentNumber() {
        return userDocumentNumber;
    }

    public void setUserDocumentNumber(String userDocumentNumber) {
        this.userDocumentNumber = userDocumentNumber;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserCodeCountry() {
        return userCodeCountry;
    }

    public void setUserCodeCountry(String userCodeCountry) {
        this.userCodeCountry = userCodeCountry;
    }
}
