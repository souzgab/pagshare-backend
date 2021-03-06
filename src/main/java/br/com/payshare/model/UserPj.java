package br.com.payshare.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserPj extends User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;
    private String cnpj;
    private String companyName;
//    private List<Withdraw> withdrawList;

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

//    public List<Withdraw> getWithdrawList() {
//        return withdrawList;
//    }
//
//    public void setWithdrawList(List<Withdraw> withdrawList) {
//        this.withdrawList = withdrawList;
//    }

}
