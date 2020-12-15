package br.com.payshare.utils.security;

import br.com.payshare.dto.UserDto;
import br.com.payshare.model.UserPf;

public class EntityToDto {
    public UserDto entityToUserDto(UserPf userPf , String token){
        UserDto dto = new UserDto();
        dto.setId(userPf.getUserId());
        dto.setEmail(userPf.getEmail());
        dto.setName(userPf.getName());
        dto.setToken(token);
        return dto;
    }
}
