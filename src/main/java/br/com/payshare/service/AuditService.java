package br.com.payshare.service;

import br.com.payshare.model.Audit;

import java.math.BigDecimal;

public interface AuditService {
    void insertAmount(Audit amount);
    void auditInfo();
}
