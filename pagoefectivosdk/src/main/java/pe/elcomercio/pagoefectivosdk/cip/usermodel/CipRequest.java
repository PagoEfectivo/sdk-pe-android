package pe.elcomercio.pagoefectivosdk.cip.usermodel;

import java.util.Date;

import pe.elcomercio.pagoefectivosdk.util.Currency;
import pe.elcomercio.pagoefectivosdk.util.DocumentType;

public class CipRequest {

    private Currency currency;
    private double amount;
    private String transactionCode;
    private String adminEmail;
    private Date dateExpiry;
    private String paymentConcept;
    private String additionalData;
    private String userEmail;
    private String userName;
    private String userLastName;
    private String userUbigeo;
    private String userCountry;
    private DocumentType userDocumentType;
    private String userDocumentNumber;
    private String userPhone;
    private String userCodeCountry;
    private int serviceId;

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
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

    public Date getDateExpiry() {
        return dateExpiry;
    }

    public void setDateExpiry(Date dateExpiry) {
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

    public DocumentType getUserDocumentType() {
        return userDocumentType;
    }

    public void setUserDocumentType(DocumentType userDocumentType) {
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

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }
}
