package br.com.payshare.serviceImpl;

import br.com.payshare.model.Audit;
import br.com.payshare.repository.AuditRepository;
import br.com.payshare.service.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AuditServiceImpl implements AuditService {

    AuditRepository auditRepository;

    @Autowired
    public AuditServiceImpl(AuditRepository auditRepository) {this.auditRepository = auditRepository;}

    @Override
    public Audit findById(long id) {
        return null;
    }

    @Override
    public Audit save(Audit audit) {
        return null;
    }

    @Override
    public List<Audit> findAll() {
        return null;
    }
}
