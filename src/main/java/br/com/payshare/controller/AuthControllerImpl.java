package br.com.payshare.controller;

import br.com.payshare.api.AuthController;
import br.com.payshare.dto.LoginUserDto;
import br.com.payshare.repository.UserRepository;
import br.com.payshare.service.UserPfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthControllerImpl implements AuthController {

    private UserPfService userPfService;

    @Autowired
    public AuthControllerImpl(UserPfService userPfService) {
        this.userPfService = userPfService;
    }

    @Override
    public ResponseEntity auth(LoginUserDto user) {
        try {
            LoginUserDto authUser = userPfService.findByCpf(user.getCpf());

            if (authUser == null)
                return new ResponseEntity(null, HttpStatus.NOT_FOUND);

            if (!user.getPassword().equals(authUser.getPassword()))
                return new ResponseEntity(null, HttpStatus.MOVED_PERMANENTLY);

            return new ResponseEntity<>(null, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        }
    }
}
