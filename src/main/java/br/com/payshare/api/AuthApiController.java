package br.com.payshare.api;

import br.com.payshare.PayshareApplication;
import br.com.payshare.dto.LoginUserDto;
import br.com.payshare.model.UserPf;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(PayshareApplication.API_PREFIX + "/payshare/auth")
public interface AuthApiController {

    @PostMapping(value = "/login")
    ResponseEntity<?> login(@RequestBody LoginUserDto user) throws Exception;

    @PostMapping(value = "/signup")
    ResponseEntity<?> signup(@RequestBody UserPf user);

}
