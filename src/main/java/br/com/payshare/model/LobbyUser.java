package br.com.payshare.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "LOBBY_USER")
public class LobbyUser implements Serializable {

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

    @Column(name = "LOBBY_AMOUNT_TOTAL" , nullable = false , columnDefinition = "numeric default 0")
    private BigDecimal amountTotal;

    @Column(name = "LOBBY_CREATION_DATE")
    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    private LocalDateTime creationDate;

    @Column(name = "LOBBY_EXPIRATION_DATE")
    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    private LocalDateTime expirationDate;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    @JsonBackReference
    private UserPf userPf;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LobbyUser lobbyUser = (LobbyUser) o;
        return id == lobbyUser.id &&
                Objects.equals(lobbyDescription, lobbyUser.lobbyDescription) &&
                Objects.equals(orderDescription, lobbyUser.orderDescription) &&
                Objects.equals(amount, lobbyUser.amount) &&
                Objects.equals(amountTotal, lobbyUser.amountTotal) &&
                Objects.equals(creationDate, lobbyUser.creationDate) &&
                Objects.equals(expirationDate, lobbyUser.expirationDate) &&
                Objects.equals(userPf, lobbyUser.userPf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lobbyDescription, orderDescription, amount, amountTotal, creationDate, expirationDate, userPf);
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

    public BigDecimal getAmountTotal() {
        return amountTotal;
    }

    public void setAmountTotal(BigDecimal amountTotal) {
        this.amountTotal = amountTotal;
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

    public UserPf getUserPf() {
        return userPf;
    }

    public void setUserPf(UserPf userPf) {
        this.userPf = userPf;
    }
}
