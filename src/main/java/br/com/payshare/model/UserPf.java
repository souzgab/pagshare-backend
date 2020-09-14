package br.com.payshare.model;

import java.util.List;
/*** @Autor vinicius Alves ***/
public class UserPf extends User {

    private String cpf;
    private String rg;
    private List<CardSchema> cardSchemaList;

    public UserPf(String name, Integer age, String address, String city, String cep, String state, String email, String password, String cpf, String rg, List<CardSchema> cardSchemaList) {
        super(name, age, address, city, cep, state, email, password);
        this.cpf = cpf;
        this.rg = rg;
        this.cardSchemaList = cardSchemaList;
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
