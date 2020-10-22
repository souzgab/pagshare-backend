package br.com.payshare.service;

import br.com.payshare.model.Audit;
import br.com.payshare.model.Lobby;

import java.util.List;

public interface AuditService {
    Audit findById (long id);
    Audit save (Audit audit);
    List<Audit> findAll();
    Audit findAuditByFkId(long l);
}
