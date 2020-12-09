package br.com.payshare.controller;

import br.com.payshare.api.LobbyUserApiController;
import br.com.payshare.model.LobbyUser;
import br.com.payshare.service.LobbyUserService;
import br.com.payshare.service.UserPfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LobbyUserController implements LobbyUserApiController {

    UserPfService userPfService;
    LobbyUserService lobbyUserService;

    @Autowired
    public LobbyUserController(UserPfService userPfService, LobbyUserService lobbyUserService) {
        this.userPfService = userPfService;
        this.lobbyUserService = lobbyUserService;
    }

    @Override
    public ResponseEntity<?> findAll() {
        return null;
    }

    @Override
    public ResponseEntity<?> findById(long id) {
        return null;
    }

    @Override
    public ResponseEntity<?> create(LobbyUser lobbyUser) {
        return null;
    }

    @Override
    public ResponseEntity<List<LobbyUser>> findLobbyByUser(long id) {
        return null;
    }
}
