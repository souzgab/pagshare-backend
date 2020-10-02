package br.com.payshare.service;

import br.com.payshare.model.Lobby;

import java.util.List;

public interface LobbyService {
    Lobby findById(long id);
    Lobby save (Lobby lobby);
    void deleteById(long id);
    List<Lobby> findAll();
}
