package br.com.payshare.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class UserPf extends User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;
    private String cpf;
    private String rg;

    @ManyToOne
    @JoinColumn(name = "fk_belongsToLobby")
    private Lobby lobbySession;
//    private List<CardSchema> cardSchemaList;

    public UserPf(){

    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    public Lobby getLobbySession() {
        return lobbySession;
    }

    public void setLobbySession(Lobby lobbySession) {
        this.lobbySession = lobbySession;
    }

    //    public List<CardSchema> getCardSchemaList() {
//        return cardSchemaList;
//    }
//
//    public void setCardSchemaList(List<CardSchema> cardSchemaList) {
//        this.cardSchemaList = cardSchemaList;
//    }
//
//    public void addCardSchema(CardSchema cardSchema){
//        cardSchemaList.add(cardSchema);
//    }
}
