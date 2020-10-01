package br.com.payshare.repository;

import br.com.payshare.model.Lobby;
import br.com.payshare.model.User;
import br.com.payshare.model.UserPf;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface UserRepository extends CrudRepository<UserPf, Integer> {
    List<UserPf> findByLobbySession(Lobby lobby);
    UserPf findByUserId(int id);
}
