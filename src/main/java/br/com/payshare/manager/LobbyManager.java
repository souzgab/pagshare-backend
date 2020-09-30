package br.com.payshare.manager;

import br.com.payshare.model.Lobby;
import br.com.payshare.model.User;
import br.com.payshare.model.UserPf;
import br.com.payshare.repository.LobbyRepository;
import br.com.payshare.repository.UserRepository;

import java.util.Optional;

public class LobbyManager {

    private LobbyRepository lobbyRepository;
    private UserRepository userRepository;

    public LobbyManager(){

    }

    public void addParticipante(int lobbyId, int userId){
        System.out.println("Entrei");
       try {
           Lobby x = lobbyRepository.findById(lobbyId).get();
           UserPf user = userRepository.findById(userId).get();
           System.out.println("Achei a lobby" + x);
           System.out.println("E Adicionei o usuario " + user);
       }catch (Exception e){
           System.out.println(e);
       }
    }
}
