package br.com.payshare.controller;

import br.com.payshare.api.AuthApiController;
import br.com.payshare.dto.LoginUserDto;
import br.com.payshare.model.Roles;
import br.com.payshare.model.UserPf;
import br.com.payshare.service.UserPfService;
import br.com.payshare.utils.JwtResponse;
import br.com.payshare.utils.security.jwt.JwtUtils;
import br.com.payshare.utils.security.services.impl.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sun.plugin.liveconnect.SecurityContextHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AuthControllerImpl implements AuthApiController {

    private UserPfService userPfService;

    private AuthenticationManager authenticationManager;

    private JwtUtils jwtUtils;

    @Autowired
    public AuthControllerImpl(UserPfService userPfService, AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.userPfService = userPfService;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public ResponseEntity<?> login(@RequestBody LoginUserDto user) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        System.out.println(roles);

        JwtResponse response = new JwtResponse(
                jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles);

        System.out.println(response);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> signup(@RequestBody UserPf user) {

        if (!(user instanceof UserPf))
            return ResponseEntity.badRequest().body("Incorrect object");

        if (userPfService.findByEmail(user.getEmail()) != null)
            return ResponseEntity.badRequest().body("User are exists");

        Roles roles = new Roles();
        roles.setRoles("ROLE_USER");
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

        return null;
    }
}
