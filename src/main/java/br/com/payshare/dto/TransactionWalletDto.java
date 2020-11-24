package br.com.payshare.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class TransactionWalletDto {
    @JsonProperty
    private long transactionId;

    @NotNull
    @JsonProperty
    private BigDecimal amount;

    @JsonProperty
    private String status;

    @JsonProperty
    private String description;

    @JsonProperty
    private String paymentMethod;

    @JsonProperty
    private String currencyId;

    @JsonProperty
    private String externalReference;

    @JsonProperty
    private LocalDateTime createdAt;

    @JsonProperty
    private LocalDateTime expirationDate;

    @JsonProperty
    private String initPoint;

    @JsonProperty
    private long userPf;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionWalletDto that = (TransactionWalletDto) o;
        return transactionId == that.transactionId &&
                userPf == that.userPf &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(status, that.status) &&
                Objects.equals(description, that.description) &&
                Objects.equals(paymentMethod, that.paymentMethod) &&
                Objects.equals(currencyId, that.currencyId) &&
                Objects.equals(externalReference, that.externalReference) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(expirationDate, that.expirationDate) &&
                Objects.equals(initPoint, that.initPoint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId, amount, status, description, paymentMethod, currencyId, externalReference, createdAt, expirationDate, initPoint, userPf);
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public String getExternalReference() {
        return externalReference;
    }

    public void setExternalReference(String externalReference) {
        this.externalReference = externalReference;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getInitPoint() {
        return initPoint;
    }

    public void setInitPoint(String initPoint) {
        this.initPoint = initPoint;
    }

    public long getUserPf() {
        return userPf;
    }

    public void setUserPf(long userPf) {
        this.userPf = userPf;
    }
}
