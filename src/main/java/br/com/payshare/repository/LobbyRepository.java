package br.com.payshare.repository;

import br.com.payshare.model.Lobby;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface LobbyRepository extends CrudRepository<Lobby, Integer> {
    Lobby findByLobbyId(int id);
}
