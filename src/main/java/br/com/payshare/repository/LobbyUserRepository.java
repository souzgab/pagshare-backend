package br.com.payshare.repository;

import br.com.payshare.model.LobbyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LobbyUserRepository extends JpaRepository<LobbyUser, Long> {
    LobbyUser findById(long id);
}
