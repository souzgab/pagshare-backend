package br.com.payshare.controller;

import br.com.payshare.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/*** @Autor vinicius Alves , Gabriel Souza ***/

@RestController
@RequestMapping(value = "/api/v1/payshare")
public class LobbyController {

    private List<OrderService> orderServices;
    private List<User> userPfs;
    private List<Lobby> lobbies;
    private List<User> usuarioLogado;

    public LobbyController() {
        this.orderServices = new ArrayList<>();
        this.userPfs = new ArrayList<>();
        this.lobbies = new ArrayList<>();
        this.usuarioLogado = new ArrayList<>();
    }

    //Get all clients
    @GetMapping(value = "/clients")
    public ResponseEntity findAll() {
        if (userPfs.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(userPfs);
        }
    }
    // endpoint order-service
    @GetMapping(value = "/order-service")
    public ResponseEntity findOrderService() {
        if (orderServices.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(orderServices);
        }
    }

    //Get all lobbies from controller
    @GetMapping(value = "/lobbies")
    public ResponseEntity findLobbies() {
        if (lobbies.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(lobbies);
        }
    }

    // Create User for insert orderService
    @PostMapping(value = "/clients")
    public ResponseEntity createUser(@RequestBody UserPf userPf) {
        userPfs.add(userPf);
        return ResponseEntity.created(null).build();
    }

    // Create OrderService for set Lobby orderService object
    // Set Loby description
    // Add lobby in ArrayList lobbies
    @PostMapping(value = "/create-orderservice")
    public ResponseEntity createOrderService(@RequestBody OrderService orderService) {
        orderServices.add(orderService);
        return ResponseEntity.created(null).build();
    }

    @PutMapping(value = "/clients/{id}")
    public ResponseEntity updateClients(@RequestBody UserPf userPf, @PathVariable int id) {
        if (userPfs.size() >= id) {
            userPfs.set(id - 1, userPf);
            return ResponseEntity.ok(null);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value = "/order-service/{id}")
    public ResponseEntity updateClients(@RequestBody OrderService oderService, @PathVariable int id) {
        if (orderServices.size() >= id) {
            orderServices.set(id - 1, oderService);
            return ResponseEntity.ok(null);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/clients/{id}")
    public ResponseEntity deleteClient(@PathVariable int id) {
        if (userPfs.size() >= id) {
            userPfs.remove(id - 1);
            return ResponseEntity.ok(null);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/order-service/{id}")
    public ResponseEntity deleteOrderService(@PathVariable int id) {
        if (orderServices.size() >= id) {
            orderServices.remove(id - 1);
            return ResponseEntity.ok(null);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    // TO DO END POINT LOBBIES IS NOT CREATED

    // Login Service for tests

    @PostMapping(value="/login")
    public ResponseEntity createSession(@RequestBody UserPf user){

        for(User usuario : userPfs){
            if (usuario instanceof UserPf){
                String email = user.getEmail();
                String password = user.getPassword();
                if(email.equals(usuario.getEmail()) && password.equals(usuario.getPassword())){
                    usuarioLogado.add(usuario);
                }else{
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
                }
            }
        }

        return ResponseEntity.ok(usuarioLogado.get(0).getName() + " você foi Logado com Sucesso");
    }

    @DeleteMapping(value="/session/{id}")
    public ResponseEntity deleteSession(@PathVariable int id){
        if (usuarioLogado.size() >= id) {
            String nome = usuarioLogado.get(id - 1).getName();
            usuarioLogado.remove(id - 1);
            return ResponseEntity.ok(nome + " você foi Deslogado com sucesso!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
