package br.com.payshare.controller;

import br.com.payshare.model.Lobby;
import br.com.payshare.model.UserPf;
import br.com.payshare.repository.LobbyRepository;
import br.com.payshare.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity createLobby(@RequestBody Lobby lobby) {
        lobbyRepository.save(lobby);
        return ResponseEntity.created(null).build();
    }

    @PostMapping(value = "/{idUser}/{idLobby}")
    public ResponseEntity addP(@PathVariable int idUser,@PathVariable int idLobby) {
        UserPf userpf = user.findByUserId(idUser);
        System.out.println(userpf);
        Lobby lobby = lobbyRepository.findByLobbyId(idLobby);
        userpf.setLobbySession(lobby);
        user.save(userpf);
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

    @GetMapping("/userLobby/{id}")
    public ResponseEntity<?> getLobby(@PathVariable int id){
        Lobby lobby = lobbyRepository.findByLobbyId(id);
        List<UserPf> userPfList = user.findByLobbySession(lobby);
        return new ResponseEntity<>(
                userPfList, HttpStatus.OK
        );
    }

}
