package br.com.payshare.repository;

import br.com.payshare.model.Lobby;
import br.com.payshare.model.User;
import br.com.payshare.model.UserPf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserPf, Long> {
    UserPf findByUserId(long id);
    List<UserPf> findByLobby(Lobby lobby);
}
