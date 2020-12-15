package br.com.payshare.service;

import br.com.payshare.model.Roles;

public interface RoleService {
    Roles findByName(String name);
    Roles save (Roles role);
}
