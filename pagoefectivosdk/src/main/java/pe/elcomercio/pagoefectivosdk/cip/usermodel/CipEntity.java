package pe.elcomercio.pagoefectivosdk.cip.usermodel;

import java.io.Serializable;

public class CipEntity implements Serializable {

    private int cip;
    private String transactionCode;
    private String dateExpiry;
    private String currency;
    private double amount;
    private String cipUrl;

    public CipEntity(int cip,
                      String transactionCode,
                      String dateExpiry,
                      String currency,
                      double amount,
                      String cipUrl) {
        this.cip = cip;
        this.transactionCode = transactionCode;
        this.dateExpiry = dateExpiry;
        this.currency = currency;
        this.amount = amount;
        this.cipUrl = cipUrl;
    }

    public int getCip() {
        return cip;
    }

    public String getTransactionCode() {
        return transactionCode;
    }

    public String getDateExpiry() {
        return dateExpiry;
    }

    public String getCurrency() {
        return currency;
    }

    public double getAmount() {
        return amount;
    }

    public String getCipUrl() {
        return cipUrl;
    }

}
