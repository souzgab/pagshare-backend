package br.com.payshare.model;
/*** @Autor vinicius Alves ***/
public class CardSchema {
    private String cardNumber;
    private String expirationDate;
    private String cardHolderName;
    private String cvv;

    public CardSchema(String cardNumber, String expirationDate, String cardHolderName, String cvv) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cardHolderName = cardHolderName;
        this.cvv = cvv;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    @Override
    public String toString() {
        return "CardSchema{" +
                "cardNumber='" + cardNumber + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", cardHolderName='" + cardHolderName + '\'' +
                ", cvv='" + cvv + '\'' +
                '}';
    }
}
