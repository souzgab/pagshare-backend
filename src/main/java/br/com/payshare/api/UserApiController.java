package br.com.payshare.api;


import br.com.payshare.PayshareApplication;
import br.com.payshare.model.UserPf;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(PayshareApplication.API_PREFIX + "/payshare/user")
public interface UserApiController {
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<UserPf>> findAll();

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserPf> findById(@PathVariable long id);

    @PostMapping(path = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> save(@RequestBody UserPf userPf)
            throws InstantiationException, IllegalAccessException;

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> update(@RequestBody UserPf userPf, @PathVariable long id)
            throws InstantiationException, IllegalAccessException;

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> delete(@PathVariable long id);

    @GetMapping(path = "/lobby/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<UserPf>> findUsersByLobby(@PathVariable long id);
}
