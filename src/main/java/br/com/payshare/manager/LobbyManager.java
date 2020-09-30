package br.com.payshare.manager;

import br.com.payshare.model.Lobby;
import br.com.payshare.model.User;
import br.com.payshare.model.UserPf;
import br.com.payshare.repository.LobbyRepository;
import br.com.payshare.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LobbyManager {

    @Autowired
    private LobbyRepository lobbyRepository;

    @Autowired
    private UserRepository userRepository;

    public LobbyManager(){

    }

    public void addParticipante(int lobbyId, int userId){
       System.out.println("Entrei");
       System.out.println(lobbyRepository.findAll());
    }
}
