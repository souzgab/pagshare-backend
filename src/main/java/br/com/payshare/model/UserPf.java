package br.com.payshare.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.validation.constraints.NotNull;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "USER_PF", indexes = {@Index(name = "USER_PF_EMAIL", columnList = "USER_EMAIL" , unique = true)})
public class UserPf extends User{

    @NotNull
    @Column(name = "USER_CPF", length = 14, nullable = false)
    private String cpf;

    @Column(name = "USER_RG" , length = 9 , nullable = true)
    private String rg;

    @Column(name = "USER_LOBBY_HOST" , length = 1 , nullable = true , columnDefinition = "boolean default false")
    private boolean userLobbyHost;

    @Column(name = "USER_AMOUNT_LOBBY" , length = 3 , nullable = true)
    private BigDecimal userAmountLobby;

    @ManyToOne
    @JoinColumn(name = "LOBBY_ID")
    @JsonBackReference
    private Lobby lobby;

    public UserPf() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPf userPf = (UserPf) o;
        return userLobbyHost == userPf.userLobbyHost &&
                Objects.equals(cpf, userPf.cpf) &&
                Objects.equals(rg, userPf.rg) &&
                Objects.equals(lobby, userPf.lobby);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf, rg, userLobbyHost, lobby);
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Lobby getLobby() {
        return lobby;
    }

    public void setLobby(Lobby lobby) {
        this.lobby = lobby;
    }

    public boolean isUserLobbyHost() {
        return userLobbyHost;
    }

    public void setUserLobbyHost(boolean userLobbyHost) {
        this.userLobbyHost = userLobbyHost;
    }

    public BigDecimal getUserAmountLobby() {
        return userAmountLobby;
    }

    public void setUserAmountLobby(BigDecimal userAmountLobby) {
        this.userAmountLobby = userAmountLobby;
    }
}
