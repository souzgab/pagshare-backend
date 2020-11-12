package br.com.payshare.model;

import javax.persistence.*;

@Entity
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROLES_ID" , nullable = false)
    private long id;

    @Column(name = "ROLES_NAME" , nullable = false)
    private String roles;

    public Roles () {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
