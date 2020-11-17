package br.com.payshare.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity
public class Roles implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private List<UserPf> users;

    public Roles () {

    }

    @Override
    public String getAuthority() {
        return name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserPf> getUsers() {
        return users;
    }

    public void setUsers(List<UserPf> users) {
        this.users = users;
    }
}
