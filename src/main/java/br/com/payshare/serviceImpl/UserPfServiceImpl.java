package br.com.payshare.serviceImpl;

import br.com.payshare.dto.LoginUserDto;
import br.com.payshare.model.Lobby;
import br.com.payshare.model.UserPf;
import br.com.payshare.repository.UserRepository;
import br.com.payshare.service.UserPfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPfServiceImpl implements UserPfService {


    UserRepository userRepository;

    @Autowired
    public UserPfServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserPf findByUserId(long id) {
        return userRepository.findByUserId(id);
    }

    @Override
    public UserPf save(UserPf userPf) {
        return userRepository.save(userPf);
    }

    @Override
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserPf> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<UserPf> findByLobby(Lobby lobby) {
        return userRepository.findByLobby(lobby);
    }

    @Override
    public LoginUserDto findByCpf(String cpf) {
        return userRepository.findByCpf(cpf);
    }
}
