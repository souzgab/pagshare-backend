package br.com.payshare.service;

import br.com.payshare.dto.LoginUserDto;
import br.com.payshare.model.Lobby;
import br.com.payshare.model.UserPf;

import java.util.List;

public interface UserPfService {
    UserPf findByUserId(long id);
    UserPf findByCpf(String cpf);
    UserPf save(UserPf userPf);
    void deleteById(long id);
    void flush();
    List<UserPf> findAll();
    List<UserPf> findByLobby(Lobby lobby);
    UserPf findByEmail(String email);
}
