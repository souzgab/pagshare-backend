package br.com.payshare.service.impl;

import br.com.payshare.model.Audit;
import br.com.payshare.model.Lobby;
import br.com.payshare.repository.AuditRepository;
import br.com.payshare.service.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditServiceImpl implements AuditService {

    AuditRepository auditRepository;

    @Autowired
    public AuditServiceImpl(AuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }

    @Override
    public Audit findById(long id) {
        return auditRepository.findById(id);
    }

    @Override
    public Audit save(Audit audit) {
        return auditRepository.save(audit);
    }

    @Override
    public List<Audit> findAll() {
        return auditRepository.findAll();
    }

    @Override
    public Audit findByIdLobby(long idLobby) {
        return auditRepository.findByIdLobby(idLobby);
    }
}
