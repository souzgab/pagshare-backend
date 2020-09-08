package br.com.payshare.model;

import java.util.List;
/*** @Autor vinicius Alves ***/
public class OrderService {
    private String oderDescription;
    private Double amount;
    private String orderDate;
    private List<UserPf> userPfList;

    public OrderService(String oderDescription, Double amount, String orderDate, List<UserPf> userPfList) {
        this.oderDescription = oderDescription;
        this.amount = amount;
        this.orderDate = orderDate;
        this.userPfList = userPfList;
    }

    public String getOrderDescription() {
        return oderDescription;
    }

    public void setOrderDescription(String oderDescription) {
        this.oderDescription = oderDescription;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amout) {
        this.amount = amout;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public List<UserPf> getUserPfList() {
        return userPfList;
    }

    public void setUserPfList(List<UserPf> userPfList) {
        this.userPfList = userPfList;
    }

    public void addUserPf(UserPf userPf) {
        userPfList.add(userPf);
    }

    @Override
    public String toString() {
        return "OrderService{" +
                "orderDescription='" + oderDescription + '\'' +
                ", amout=" + amount +
                ", orderDate='" + orderDate + '\'' +
                ", userPfList=" + userPfList +
                '}';
    }
}
