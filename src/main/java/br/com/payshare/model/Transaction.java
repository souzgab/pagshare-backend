package br.com.payshare.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "TRANSACTION")
public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "TRANSACTION_ID" , nullable = false)
    private long transactionId;

    @NotNull
    @Column(name = "TRANSACTION_AMOUNT" , nullable = false)
    private BigDecimal amount;

    @NotNull
    @Column(name = "TRANSACTION_STATUS" , nullable = false)
    private String status;

    @NotNull
    @Column(name = "TRANSACTION_DESCRIPTION" , nullable = false , length = 255)
    private String description;

    @Column(name = "TRANSACTION_PAYMENT_METHOD")
    private String paymentMethod;

    @NotNull
    @Column(name = "TRANSACTION_CURRENCYID" ,nullable = false , length = 3)
    private String currencyId;

    @NotNull
    @Column(name = "TRANSACTION_EXTERNAL_REFERENCES" , nullable = false)
    private String externalReference;

    @NotNull
    @Column(name = "CREATED_AT" , nullable = false)
    private LocalDateTime createdAt;

    @NotNull
    @Column(name = "EXPIRATION_DATE", nullable = false)
    private LocalDateTime expirationDate;

    @NotNull
    @Column(name = "INIT_POINT", nullable = false)
    private String initPoint;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    @JsonBackReference
    private UserPf userPf;

    @ManyToOne
    @JoinColumn(name = "LOBBY_ID")
    @JsonBackReference
    private Lobby lobby;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return transactionId == that.transactionId &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(status, that.status) &&
                Objects.equals(description, that.description) &&
                Objects.equals(paymentMethod, that.paymentMethod) &&
                Objects.equals(currencyId, that.currencyId) &&
                Objects.equals(externalReference, that.externalReference) &&
                Objects.equals(userPf, that.userPf) &&
                Objects.equals(lobby, that.lobby);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId, amount, status, description, paymentMethod, currencyId, externalReference, userPf, lobby);
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

    public UserPf getUserPf() {
        return userPf;
    }

    public void setUserPf(UserPf userPf) {
        this.userPf = userPf;
    }

    public Lobby getLobby() {
        return lobby;
    }

    public void setLobby(Lobby lobby) {
        this.lobby = lobby;
    }


}
