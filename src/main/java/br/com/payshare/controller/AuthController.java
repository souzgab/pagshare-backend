package br.com.payshare.controller;

import br.com.payshare.api.AuthApiController;
import br.com.payshare.dto.LoginUserDto;
import br.com.payshare.model.Roles;
import br.com.payshare.model.UserPf;
import br.com.payshare.service.RoleService;
import br.com.payshare.service.UserPfService;
import br.com.payshare.utils.security.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AuthController implements AuthApiController {


    private JwtUtil jwtUtil;
    private AuthenticationManager authenticationManager;
    private UserPfService userPfService;
    private RoleService roleService;

    @Autowired
    public AuthController(JwtUtil jwtUtil, AuthenticationManager authenticationManager,
                          UserPfService userPfService, RoleService roleService) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.userPfService = userPfService;
        this.roleService = roleService;
    }

    @Override
    public ResponseEntity<?> login(LoginUserDto user) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("inavalid username/password");
        }
        return new ResponseEntity<>(jwtUtil.generateToken(user.getEmail()), HttpStatus.OK) ;
    }

    @Override
    public ResponseEntity<?> signup(@RequestBody UserPf user) {

        if (userPfService.findByEmail(user.getEmail()) != null)
            return ResponseEntity.badRequest().body("User are exists");

        Roles roles = roleService.findByName("ROLE_USER");
        List<Roles> rolesList = new ArrayList<>();
        rolesList.add(roles);

        UserPf newUser = new UserPf();
        newUser.setName(user.getName());
        newUser.setAge(user.getAge());
        newUser.setEmail(user.getEmail());
        newUser.setCpf(user.getCpf());
        newUser.setAddress(user.getAddress());
        newUser.setCity(user.getCity());
        newUser.setCep(user.getCep());
        newUser.setState(user.getState());
        newUser.setRg(user.getRg());
        newUser.setRoles(rolesList);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        newUser.setPassword(encoder.encode(user.getPassword()));
        userPfService.save(newUser);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }
}
