package pe.elcomercio.pagoefectivosdk.cip.usermodel;

import java.io.Serializable;

public class CipSearch implements Serializable {

    private int cip;
    private String transactionCode;
    private double amount;
    private String currency;
    private int status;
    private String statusName;
    private String dateCreation;
    private String dateExpiry;
    private String datePayment;
    private String dateRemoval;

    public int getCip() {
        return cip;
    }

    public void setCip(int cip) {
        this.cip = cip;
    }

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getDateExpiry() {
        return dateExpiry;
    }

    public void setDateExpiry(String dateExpiry) {
        this.dateExpiry = dateExpiry;
    }

    public String getDatePayment() {
        return datePayment;
    }

    public void setDatePayment(String datePayment) {
        this.datePayment = datePayment;
    }

    public String getDateRemoval() {
        return dateRemoval;
    }

    public void setDateRemoval(String dateRemoval) {
        this.dateRemoval = dateRemoval;
    }
}
