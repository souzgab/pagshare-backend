package br.com.payshare.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.validation.constraints.NotNull;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "USER_PF", indexes = {@Index(name = "USER_PF_EMAIL", columnList = "USER_EMAIL" , unique = true)})
public class UserPf extends User{

    @NotNull
    @Column(name = "USER_CPF", length = 14, nullable = false)
    private String cpf;

    @Column(name = "USER_RG" , length = 9 , nullable = true)
    private String rg;

    @Column(name = "USER_AMOUNT", nullable = true)
    private BigDecimal userAmount;

    @Column(name = "USER_LOBBY_HOST" , length = 1 , nullable = true , columnDefinition = "boolean default false")
    private boolean userLobbyHost;

    @Column(name = "USER_AMOUNT_LOBBY" , length = 3 , nullable = true)
    private BigDecimal userAmountLobby;

    @ManyToOne
    @JoinColumn(name = "LOBBY_ID")
    @JsonBackReference
    private Lobby lobby;

    @OneToMany
    @JoinColumn(name = "USER_ID")
    private List<Transaction> transactions = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "USER_ID")
    private List<TransactionWallet> transactionWallets = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "USER_ID")
    private List<LobbyUser> lobbyUserList = new ArrayList<>();

    @ManyToMany()
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private List<Roles> roles;

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
                Objects.equals(userAmount, userPf.userAmount) &&
                Objects.equals(userAmountLobby, userPf.userAmountLobby) &&
                Objects.equals(lobby, userPf.lobby) &&
                Objects.equals(transactions, userPf.transactions) &&
                Objects.equals(transactionWallets, userPf.transactionWallets) &&
                Objects.equals(roles, userPf.roles);
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

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

    public BigDecimal getUserAmount() {
        return userAmount;
    }

    public void setUserAmount(BigDecimal userAmount) {
        this.userAmount = userAmount;
    }

    public List<TransactionWallet> getTransactionWallets() {
        return transactionWallets;
    }

    public void setTransactionWallets(List<TransactionWallet> transactionWallets) {
        this.transactionWallets = transactionWallets;
    }

    public List<LobbyUser> getLobbyUserList() {
        return lobbyUserList;
    }

    public void setLobbyUserList(List<LobbyUser> lobbyUserList) {
        this.lobbyUserList = lobbyUserList;
    }
}
