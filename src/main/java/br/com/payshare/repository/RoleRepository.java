package br.com.payshare.repository;

import br.com.payshare.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Roles,Long> {
    Roles findByName(String name);
}
