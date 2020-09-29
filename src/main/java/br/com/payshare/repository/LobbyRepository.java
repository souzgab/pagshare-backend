package br.com.payshare.repository;

import br.com.payshare.model.Lobby;
import org.springframework.data.repository.CrudRepository;

public interface LobbyRepository extends CrudRepository<Lobby, Long> {
}
