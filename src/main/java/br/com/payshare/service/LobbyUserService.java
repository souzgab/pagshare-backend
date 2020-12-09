package br.com.payshare.service;

import br.com.payshare.model.LobbyUser;


public interface LobbyUserService {
    LobbyUser findById(long id);
    LobbyUser save(LobbyUser lobbyUser);
}
