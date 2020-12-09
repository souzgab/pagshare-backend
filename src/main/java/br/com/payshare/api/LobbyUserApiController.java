package br.com.payshare.api;

import br.com.payshare.PayshareApplication;
import br.com.payshare.model.LobbyUser;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(PayshareApplication.API_PREFIX + "/payshare/lobby-user")
public interface LobbyUserApiController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> findAll();

    @GetMapping(path = "/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> findById(@PathVariable long id);

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> create(@RequestBody LobbyUser lobbyUser);

    @GetMapping(path = "/lobbyUser/{iduser}",produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<LobbyUser>> findLobbyByUser(@PathVariable long id);

}
