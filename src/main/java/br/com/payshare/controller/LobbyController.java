package br.com.payshare.controller;


import br.com.payshare.api.LobbyApiController;
import br.com.payshare.model.Audit;
import br.com.payshare.model.Lobby;
import br.com.payshare.model.UserPf;
import br.com.payshare.service.AuditService;
import br.com.payshare.service.LobbyService;
import br.com.payshare.service.UserPfService;
import br.com.payshare.utils.structure.FilaObj;
import br.com.payshare.utils.structure.PilhaObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

@RestController
public class LobbyController extends Observable implements LobbyApiController {

    LobbyService lobbyService;
    UserPfService userPfService;
    AuditService auditService;
    FilaObj<Lobby> filaLobby = new FilaObj<>(500);
    PilhaObj<Audit> pilhaLobby = new PilhaObj<>(1500);
    public LobbyController(){};

    @Autowired
    public LobbyController(LobbyService lobbyService, UserPfService userPfService, AuditService auditService) {
        this.lobbyService = lobbyService;
        this.userPfService = userPfService;
        this.auditService = auditService;
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
        if (userPf.isUserLobbyHost() || userPf.getLobby() != null)
            return new ResponseEntity<>("Already_hosting_a_lobby", HttpStatus.BAD_REQUEST);
        List<UserPf> userPfList = new ArrayList<>();
        userPfList.add(userPf);
        //This Host lobby
        userPf.setUserLobbyHost(true);
        userPf.setUserAmountLobby(lobby.getAmount());
        lobby.setCreationDate(now);
        lobby.setExpirationDate(now.plusHours(48));
        lobby.setUserPfList(userPfList);
        lobbyService.save(lobby);
        this.addObserver(new AuditController());
        this.notificar(lobbyService.findById(lobby.getId()));
        return new ResponseEntity<>(lobby, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> addUserLobby(long idLobby, long id) {
        UserPf userPf = userPfService.findByUserId(id);
        Lobby lobby = lobbyService.findById(idLobby);
        List<UserPf> userPfList = userPfService.findByLobby(lobby);

        if (userPf.getLobby() != null)
            return new ResponseEntity<>("You_are_already_associated_with_a_lobby" , HttpStatus.BAD_REQUEST);
        userPfList.add(userPf);
        for (UserPf userPf1 : userPfList){
            userPf1.setUserAmountLobby(lobby.getAmount().divide(new BigDecimal(userPfList.size()), 3, RoundingMode.HALF_UP));
            userPf1.setLobby(lobby);
            try{
                lobbyService.save(lobby);
                userPfService.save(userPf1);
            }catch (Exception e){
                System.out.println("Erro ao salvar entidade: " + e.getMessage());
            }
        }
        try{
            this.addObserver(new AuditController());
            this.notificar(lobbyService.findById(lobby.getId()));
        }catch (Exception e){
            System.out.println("Erro ao comunicar Audit");
        }
        return new ResponseEntity<>(lobby, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> update(Lobby lobby, long id) throws InstantiationException, IllegalAccessException {
        Lobby lobbyEntity = lobbyService.findById(id);
        List<UserPf> userPfList = userPfService.findByLobby(lobby);
        if (lobbyEntity == null)
            return new ResponseEntity<>("You_are_already_associated_with_a_lobby" , HttpStatus.BAD_REQUEST);
        for (UserPf userPf1 : userPfList){
            userPf1.setUserAmountLobby(lobby.getAmount().divide(new BigDecimal(userPfList.size())));
            lobby.setUserPfList(userPfList);
            try{
                lobbyService.save(lobby);
                userPfService.save(userPf1);
            }catch (Exception e){
                System.out.println("Erro ao salvar entidade: " + e.getMessage());
            }
        }
        try{
            this.addObserver(new AuditController());
            this.notificar(lobbyService.findById(lobby.getId()));
        }catch (Exception e){
            System.out.println("Erro ao comunicar Audit");
        }

        return new ResponseEntity<>(lobby, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> delete(long id) {
        Lobby lobbyEntity = lobbyService.findById(id);
        List<UserPf> userPfList = userPfService.findByLobby(lobbyEntity);
        if (lobbyEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        for (UserPf userPf: userPfList){
            userPf.setUserAmountLobby(null);
            userPf.setUserLobbyHost(false);
        }
        lobbyService.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> lobbyUser(long id) {
        UserPf userPf = userPfService.findByUserId(id);
        Lobby lobby = lobbyService.findByUserPfList(userPf);
        if (userPf == null || lobby == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(lobby , HttpStatus.OK);
    }

    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
    }

    @Override
    public void notifyObservers(Object arg) {
        super.notifyObservers(arg);
    }

    private void notificar(Lobby l) {
        setChanged();
        notifyObservers(l);
    }
}
