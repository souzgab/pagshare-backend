package br.com.payshare.service.impl;

import br.com.payshare.model.LobbyUser;
import br.com.payshare.repository.LobbyUserRepository;
import br.com.payshare.service.LobbyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LobbyUserServiceImpl implements LobbyUserService {

    LobbyUserRepository lobbyUserRepository;

    @Autowired
    public LobbyUserServiceImpl(LobbyUserRepository lobbyUserRepository) {
        this.lobbyUserRepository = lobbyUserRepository;
    }

    @Override
    public LobbyUser findById(long id) {
        return lobbyUserRepository.findById(id);
    }

    @Override
    public LobbyUser save(LobbyUser lobbyUser) {
        return lobbyUserRepository.save(lobbyUser);
    }
}
