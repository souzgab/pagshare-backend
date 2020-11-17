package br.com.payshare.model;

import br.com.payshare.interfaces.Taxes;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Lobby")
public class Lobby implements Taxes , Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOBBY_ID" , nullable = false)
    private long id;

    @NotNull
    @Column(name = "LOBBY_DESCRIPTION" , length = 100 , nullable = false)
    private String lobbyDescription;

    @NotNull
    @Column(name = "LOBBY_ORDER_DESCRIPTION" , length = 100 , nullable = false)
    private String orderDescription;

    @NotNull
    @Column(name = "LOBBY_AMOUNT" , nullable = false)
    private BigDecimal amount;

    @Column(name = "LOBBY_CREATION_DATE")
    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    private LocalDateTime creationDate;

    @Column(name = "LOBBY_EXPIRATION_DATE")
    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    private LocalDateTime expirationDate;

    @OneToMany
    @JoinColumn(name = "LOBBY_ID")
    private List<UserPf> userPfList = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "LOBBY_ID")
    private List<Transaction> transactions = new ArrayList<>();

    public Lobby() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lobby lobby = (Lobby) o;
        return id == lobby.id &&
                Objects.equals(lobbyDescription, lobby.lobbyDescription) &&
                Objects.equals(orderDescription, lobby.orderDescription) &&
                Objects.equals(amount, lobby.amount) &&
                Objects.equals(creationDate, lobby.creationDate) &&
                Objects.equals(expirationDate, lobby.expirationDate) &&
                Objects.equals(userPfList, lobby.userPfList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lobbyDescription, orderDescription, amount, creationDate, expirationDate, userPfList);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLobbyDescription() {
        return lobbyDescription;
    }

    public void setLobbyDescription(String lobbyDescription) {
        this.lobbyDescription = lobbyDescription;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public List<UserPf> getUserPfList() {
        return userPfList;
    }

    public void setUserPfList(List<UserPf> userPfList) {
        this.userPfList = userPfList;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "Lobby{" +
                "id=" + id +
                ", lobbyDescription='" + lobbyDescription + '\'' +
                ", orderDescription='" + orderDescription + '\'' +
                ", amount=" + amount +
                ", creationDate=" + creationDate +
                ", expirationDate=" + expirationDate +
                ", userPfList=" + userPfList +
                '}';
    }
}
