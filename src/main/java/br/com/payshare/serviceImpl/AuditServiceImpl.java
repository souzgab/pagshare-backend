package br.com.payshare.serviceImpl;

import br.com.payshare.model.Audit;
import br.com.payshare.repository.AuditRepository;
import br.com.payshare.service.AuditService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class AuditServiceImpl implements AuditService {

    AuditRepository auditRepository;

    @Autowired
    public AuditServiceImpl(AuditRepository auditRepository){ return this.auditRepository = auditRepository}

    @Override
    public void insertAmount(Audit amount) {
        auditRepository.save(amount);
    }

    @Override
    public void auditInfo() {

    }
}
