package br.com.payshare.repository;

import br.com.payshare.model.Audit;
import br.com.payshare.model.Lobby;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuditRepository extends JpaRepository <Audit,Long> {
    Audit findById(long id);
    Audit findByIdLobby(long id);
}
