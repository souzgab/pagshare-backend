package br.com.payshare.model;

import javax.smartcardio.Card;
import java.util.ArrayList;
import java.util.List;
/*** @Autor vinicius Alves ***/
public class UserPf extends User {

    private String cpf;
    private String rg;
    private List<CardSchema> cardSchemaList;

    public UserPf(String name, Integer age, String address, String city, String state, String cpf, String rg) {
        super(name, age, address, city, state);
        this.cpf = cpf;
        this.rg = rg;
        this.cardSchemaList = new ArrayList<>();
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

    public List<CardSchema> getCardSchemaList() {
        return cardSchemaList;
    }

    public void setCardSchemaList(List<CardSchema> cardSchemaList) {
        this.cardSchemaList = cardSchemaList;
    }

    public void addCardSchema(CardSchema cardSchema){
        cardSchemaList.add(cardSchema);
    }

    @Override
    public String toString() {
        return "UserPf{" +
                "cpf='" + cpf + '\'' +
                ", rg='" + rg + '\'' +
                ", cardSchemaList=" + cardSchemaList +
                "} " + super.toString();
    }
}
