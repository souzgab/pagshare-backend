package br.com.payshare.service.impl;

import br.com.payshare.model.Lobby;
import br.com.payshare.model.UserPf;
import br.com.payshare.repository.LobbyRepository;
import br.com.payshare.service.LobbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LobbyServiceImpl implements LobbyService {


    LobbyRepository lobbyRepository;

    @Autowired
    public LobbyServiceImpl(LobbyRepository lobbyRepository) {
        this.lobbyRepository = lobbyRepository;
    }

    @Override
    public Lobby findById(long id) {
        return lobbyRepository.findById(id);
    }

    @Override
    public Lobby save(Lobby lobby) {
        return lobbyRepository.save(lobby);
    }

    @Override
    public void deleteById(long id) {
        lobbyRepository.deleteById(id);
    }

    @Override
    public List<Lobby> findAll() {
        return lobbyRepository.findAll();
    }

    @Override
    public Lobby findByUserPfList(UserPf userPf) {
        return lobbyRepository.findByUserPfList(userPf);
    }
}
