package br.com.payshare.model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotNull;
import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public abstract class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID", nullable = false)
    private long userId;

    @NotNull
    @Column(name = "USER_NAME" , nullable = false )
    private String name;

    @NotNull
    @Column(name = "USER_AGE" , nullable = false , length = 3)
    private Integer age;

    @Column(name = "USER_ADDRESS" , length = 255)
    private String address;

    @Column(name = "USER_CITY" , length = 50)
    private String city;

    @Column(name = "USER_CEP" , length = 9)
    private String cep;

    @Column(name = "USER_STATE" , length = 50)
    private String state;

    @NotNull
    @Column(name = "USER_EMAIL" , length = 200 , nullable = false)
    private String email;

    @NotNull
    @Column(name = "USER_PASSWORD" , length = 200 , nullable = false)
    @JsonIgnore
    private String password;

    public String getName() {
        return name;
    }


    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
