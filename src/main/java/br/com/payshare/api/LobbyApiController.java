package br.com.payshare.api;

import br.com.payshare.PayshareApplication;
import br.com.payshare.model.Lobby;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(PayshareApplication.API_PREFIX + "/payshare/lobby")
public interface LobbyApiController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Lobby>> findAll();

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Lobby> findById(@PathVariable long id);

    @PostMapping(path = "/saveLobby/{idUser}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> create(@RequestBody Lobby lobby, @PathVariable long idUser)
            throws InstantiationException, IllegalAccessException;

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> update(@RequestBody Lobby lobby, @PathVariable long id)
            throws InstantiationException, IllegalAccessException;

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> delete(@PathVariable long id);

    @PostMapping(path = "/{idLobby}/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> addUserLobby(@PathVariable long idLobby, @PathVariable long id);

    @GetMapping(path = "/lobbyUser/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> lobbyUser(@PathVariable long id);
}
