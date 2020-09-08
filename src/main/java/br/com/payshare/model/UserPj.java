package br.com.payshare.model;

import java.util.ArrayList;
import java.util.List;
/*** @Autor vinicius Alves ***/
public class UserPj extends User{
    private String cnpj;
    private String companyName;
    private List<Withdraw> withdrawList;
    private List<OrderService> orderServices;

    public UserPj(String name, Integer age, String address, String city, String state, String cnpj, String companyName) {
        super(name, age, address, city, state);
        this.cnpj = cnpj;
        this.companyName = companyName;
        this.withdrawList = new ArrayList<>();
        this.orderServices = new ArrayList<>();
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<Withdraw> getWithdrawList() {
        return withdrawList;
    }

    public void setWithdrawList(List<Withdraw> withdrawList) {
        this.withdrawList = withdrawList;
    }

    public List<OrderService> getOrderServices() {
        return orderServices;
    }

    public void setOrderServices(List<OrderService> orderServices) {
        this.orderServices = orderServices;
    }

    @Override
    public String toString() {
        return "UserPj{" +
                "cnpj='" + cnpj + '\'' +
                ", companyName='" + companyName + '\'' +
                ", withdrawList=" + withdrawList +
                ", orderServices=" + orderServices +
                "} " + super.toString();
    }
}
