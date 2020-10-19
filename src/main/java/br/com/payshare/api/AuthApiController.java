package br.com.payshare.api;

import br.com.payshare.PayshareApplication;
import br.com.payshare.dto.LoginUserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(PayshareApplication.API_PREFIX + "/payshare/auth")
public interface AuthApiController {

    @PostMapping
    ResponseEntity<?> auth(@RequestBody LoginUserDto user);

}
