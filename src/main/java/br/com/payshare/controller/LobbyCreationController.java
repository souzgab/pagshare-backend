package br.com.payshare.controller;

import br.com.payshare.model.UserPf;
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

    @PostMapping(value = "/clients")
    public ResponseEntity createUser(@RequestBody UserPf userPf) {
        user.save(userPf);
        return ResponseEntity.created(null).build();
    }

    @GetMapping
    public ResponseEntity<?> getUser(){
        return new ResponseEntity<>(
                user.findAll(), HttpStatus.OK
        );
    }

}
