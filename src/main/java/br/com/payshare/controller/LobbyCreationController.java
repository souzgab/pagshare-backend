package br.com.payshare.controller;

import br.com.payshare.model.Lobby;
import br.com.payshare.model.UserPf;
import br.com.payshare.repository.LobbyRepository;
import br.com.payshare.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/payshare")
public class LobbyCreationController {

    @Autowired
    private UserRepository user;

    @Autowired
    private LobbyRepository lobbyRepository;

    @PostMapping(value = "/clients")
    public ResponseEntity createUser(@RequestBody UserPf userPf) {
        user.save(userPf);
        return ResponseEntity.created(null).build();
    }

    @PostMapping(value = "/lobby")
    public ResponseEntity createUser(@RequestBody Lobby lobby, @RequestParam long id) {
        lobbyRepository.save(lobby);
        return ResponseEntity.created(null).build();
    }

    @GetMapping("/users")
    public ResponseEntity<?> getUser(){
        return new ResponseEntity<>(
                user.findAll(), HttpStatus.OK
        );
    }

    @GetMapping("/lobby")
    public ResponseEntity<?> getLobby(){
        return new ResponseEntity<>(
                lobbyRepository.findAll(), HttpStatus.OK
        );
    }

}
