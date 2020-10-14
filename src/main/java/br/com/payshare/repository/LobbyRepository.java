package br.com.payshare.repository;

import br.com.payshare.model.Lobby;
import br.com.payshare.model.UserPf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LobbyRepository extends JpaRepository<Lobby, Long> {
    Lobby findById(long id);
    Lobby findByUserPfList(UserPf userPf);
}
