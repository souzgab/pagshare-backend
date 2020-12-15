package br.com.payshare.model;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.hibernate.annotations.CreationTimestamp;
import org.json.JSONObject;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "AUDIT")
public class Audit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "AUDIT_ID", nullable = false)
    private long id;

    @NotNull
    @Column(name = "LOBBY_ID", nullable = false)
    private long idLobby;

    @NotNull
    @Column(name = "USER_ID", nullable = false)
    private long idUser;

    @NotNull
    @Column(name = "USERPF_IDS_LOBBY_HISTORY", nullable = false)
    private String idUserPfHistory;

    @NotNull
    @Column(name = "LOBBY_INFO", nullable = false)
    private String lobbyData;

    @NotNull
    @Column(name = "DESC_HST", nullable = false)
    private String descriptionHistory;

    @NotNull
    @Column(name = "CREATED_AT", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    public Audit() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Audit audit = (Audit) o;
        return id == audit.id &&
                idLobby == audit.idLobby &&
                idUser == audit.idUser &&
                Objects.equals(idUserPfHistory, audit.idUserPfHistory) &&
                Objects.equals(lobbyData, audit.lobbyData) &&
                Objects.equals(descriptionHistory, audit.descriptionHistory) &&
                Objects.equals(createdAt, audit.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idLobby, idUserPfHistory, createdAt);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdLobby() {
        return idLobby;
    }

    public void setIdLobby(long idLobby) {
        this.idLobby = idLobby;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public String getDescriptionHistory() {
        return descriptionHistory;
    }

    public void setDescriptionHistory(String descriptionHistory) {
        this.descriptionHistory = descriptionHistory;
    }

    public String getIdUserPfHistory() {
        return idUserPfHistory;
    }

    public void setIdUserPfHistory(String idUserPfHistory) {
        this.idUserPfHistory = idUserPfHistory;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getLobbyData() {
        return lobbyData;
    }

    public void setLobbyData(String lobbyData) {
        this.lobbyData = lobbyData;
    }

    @Override
    public String toString() {
        return "Audit{" +
                "id=" + id +
                ", idLobby=" + idLobby +
                ", idUser=" + idUser +
                ", idUserPfHistory='" + idUserPfHistory + '\'' +
                ", lobbyData='" + lobbyData + '\'' +
                ", descriptionHistory='" + descriptionHistory + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
