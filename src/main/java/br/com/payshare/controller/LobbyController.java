package br.com.payshare.controller;


import br.com.payshare.api.LobbyApiController;
import br.com.payshare.model.Lobby;
import br.com.payshare.model.UserPf;
import br.com.payshare.service.LobbyService;
import br.com.payshare.service.UserPfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class LobbyController implements LobbyApiController {

    LobbyService lobbyService;
    UserPfService userPfService;

    @Autowired
    public LobbyController(LobbyService lobbyService, UserPfService userPfService) {
        this.lobbyService = lobbyService;
        this.userPfService = userPfService;
    }

    @Override
    public ResponseEntity<List<Lobby>> findAll() {
        if (lobbyService.findAll().isEmpty())
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(lobbyService.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Lobby> findById(long id) {
        Lobby lobby = lobbyService.findById(id);
        if (lobby == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(lobbyService.findById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> create(Lobby lobby, long idUser) throws InstantiationException, IllegalAccessException {
        LocalDateTime now = LocalDateTime.now();
        UserPf userPf = userPfService.findByUserId(idUser);
        List<UserPf> userPfList = new ArrayList<>();
        userPfList.add(userPf);
        //This Host lobby
        userPf.setUserLobbyHost(true);
        lobby.setCreationDate(now);
        lobby.setExpirationDate(now.plusHours(48));
        lobby.setUserPfList(userPfList);
        return new ResponseEntity<>(lobbyService.save(lobby), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> addUserLobby(long idLobby, long id) {
        UserPf userPf = userPfService.findByUserId(id);
        Lobby lobby = lobbyService.findById(idLobby);
        userPf.setLobby(lobby);
        return new ResponseEntity<>(lobbyService.save(lobby), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> update(Lobby lobby, long id) throws InstantiationException, IllegalAccessException {
        Lobby lobbyEntity = lobbyService.findById(id);
        if (lobbyEntity == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(lobbyService.save(lobby), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> delete(long id) {
        Lobby lobbyEntity = lobbyService.findById(id);
        if (lobbyEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        lobbyService.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
