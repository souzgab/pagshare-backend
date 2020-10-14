package br.com.payshare.model;

import org.springframework.data.annotation.Id;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "AUDIT")
public class Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "AUDIT_ID" , nullable = false)
    private long id;

    @NotNull
    @Column(name = "LOBBY_QUANTITY" , length = 100 , nullable = false)
    private Integer lobbyQuantity;

    @NotNull
    @Column(name = "AMOUNT_TRANSACTED", length = 100, nullable = false)
    private BigDecimal amountTransacted;

    @NotNull
    @Column(name = "ACTIVED_MEMBERS", nullable = false)
    private Integer activedMembers;

    public Audit() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Audit audit = (Audit) o;
        return id == audit.id &&
                Objects.equals(lobbyQuantity, audit.lobbyQuantity) &&
                Objects.equals(amountTransacted, audit.amountTransacted) &&
                Objects.equals(activedMembers, audit.activedMembers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lobbyQuantity, amountTransacted, activedMembers);
    }

    public long getId() {return id;}

    public void setId(long id) {this.id = id;}

    public int getLobbyQuantity() {return lobbyQuantity;}

    public void setLobbyQuantity(int lobbyQuantity) {this.lobbyQuantity = lobbyQuantity; }

    public BigDecimal getAmountTransacted() {return amountTransacted;}

    public void setAmountTransacted(BigDecimal amountTransacted) {this.amountTransacted = amountTransacted;}

    public int getActivedMembers() {return activedMembers;}

    public void setActivedMembers(int activedMembers) {this.activedMembers = activedMembers;}
}
