package br.com.payshare.repository;

import br.com.payshare.model.Audit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditRepository extends JpaRepository <Audit,Long> {
    Audit findById(long id);
}
