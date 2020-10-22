package br.com.payshare.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.apache.tomcat.jni.Local;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "AUDIT")
public class Audit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "AUDIT_ID" , nullable = false)
    private long id;

    @OneToOne(cascade=CascadeType.ALL)
    private Lobby fk;

    @NotNull
    @Column(name = "AMOUNT_TRANSACTED", length = 100, nullable = false)
    private BigDecimal amountTransacted;

    @NotNull
    @Column(name = "ACTIVED_MEMBERS", nullable = false)
    private Integer activedMembers;

    @NotNull
    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;

    @Column(updatable = true, name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    public Audit() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Audit audit = (Audit) o;
        return id == audit.id &&
                Objects.equals(fk, audit.fk) &&
                Objects.equals(amountTransacted, audit.amountTransacted) &&
                Objects.equals(activedMembers, audit.activedMembers) &&
                Objects.equals(createdAt, audit.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fk, amountTransacted, activedMembers,createdAt);
    }

    public long getId() {return id;}

    public void setId(long id) {this.id = id;}

    public Lobby getLobbyId() {
        return fk;
    }

    public void setLobbyId(Lobby lobbyId) {
        this.fk = lobbyId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public BigDecimal getAmountTransacted() {return amountTransacted;}

    public void setAmountTransacted(BigDecimal amountTransacted) {this.amountTransacted = amountTransacted;}

    public int getActivedMembers() {return activedMembers;}

    public void setActivedMembers(int activedMembers) {this.activedMembers = activedMembers;}
}
