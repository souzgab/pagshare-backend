package br.com.payshare.utils.security.services.impl;


import br.com.payshare.dto.LoginUserDto;
import br.com.payshare.model.UserPf;
import br.com.payshare.service.UserPfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserPfService userPfService;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserPf userPf = userPfService.findByEmail(email);


        return UserDetailsImpl.build(userPf);
    }
}
