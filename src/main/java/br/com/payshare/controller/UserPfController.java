package br.com.payshare.controller;


import br.com.payshare.api.UserApiController;
import br.com.payshare.model.Lobby;
import br.com.payshare.model.UserPf;
import br.com.payshare.service.LobbyService;
import br.com.payshare.service.UserPfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserPfController implements UserApiController {

    UserPfService userPfService;
    LobbyService lobbyService;

    @Autowired
    public UserPfController(UserPfService userPfService, LobbyService lobbyService) {
        this.userPfService = userPfService;
        this.lobbyService = lobbyService;
    }

    @Override
    public ResponseEntity<List<UserPf>> findAll() {
        if (userPfService.findAll().isEmpty())
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(userPfService.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<UserPf>> findUsersByLobby(long id) {
        Lobby lobbyUsers = lobbyService.findById(id);
        if (lobbyUsers == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(userPfService.findByLobby(lobbyUsers), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserPf> findById(long id) {
        UserPf user = userPfService.findByUserId(id);
        if (user == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(userPfService.findByUserId(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> save(UserPf userPf) throws InstantiationException, IllegalAccessException {
        if (userPf instanceof UserPf) {
            userPfService.save(userPf);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<?> update(UserPf userPf, long id) throws InstantiationException, IllegalAccessException {
        UserPf willBe = userPfService.findByUserId(id);
        if (willBe == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(userPfService.save(userPf), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> delete(long id) {
        UserPf deleteBy = userPfService.findByUserId(id);
        if (deleteBy == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        userPfService.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }


}
