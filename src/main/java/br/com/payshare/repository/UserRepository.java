package br.com.payshare.repository;

import br.com.payshare.dto.LoginUserDto;
import br.com.payshare.model.Lobby;
import br.com.payshare.model.UserPf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserPf, Long> {
    UserPf findByUserId(long id);
    List<UserPf> findByLobby(Lobby lobby);
    LoginUserDto findByCpf(String cpf);
    UserPf findByEmail(String email);
}
