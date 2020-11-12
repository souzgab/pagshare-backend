package br.com.payshare.utils.security.services.impl;

import br.com.payshare.dto.LoginUserDto;
import br.com.payshare.model.UserPf;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails{

    private long id;

    private String name;

    @JsonIgnore
    private String cpf;

    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends  GrantedAuthority> grantedAuthorities;

    public UserDetailsImpl(long id, String name, String cpf, String email, String password, Collection<? extends GrantedAuthority> grantedAuthorities) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
        this.grantedAuthorities = grantedAuthorities;
    }

    public static UserDetails build(UserPf userPf) {
        List<GrantedAuthority> authorities = userPf.getRoles().stream()
                .map(roles -> new SimpleGrantedAuthority(roles.getRoles()))
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                userPf.getUserId(),
                userPf.getCpf(),
                userPf.getName(),
                userPf.getEmail(),
                userPf.getPassword(),
                authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public long getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDetailsImpl that = (UserDetailsImpl) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public String toString() {
        return "UserDetailsImpl{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", grantedAuthorities=" + grantedAuthorities +
                '}';
    }
}
