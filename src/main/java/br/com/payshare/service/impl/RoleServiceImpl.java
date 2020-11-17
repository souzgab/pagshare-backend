package br.com.payshare.service.impl;

import br.com.payshare.model.Roles;
import br.com.payshare.repository.RoleRepository;
import br.com.payshare.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Roles findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public Roles save(Roles role) {
        return roleRepository.save(role);
    }
}
