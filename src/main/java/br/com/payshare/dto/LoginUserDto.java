package br.com.payshare.dto;

public class LoginUserDto {

    private String email;
    private String password;

    public LoginUserDto(String cpf, String password) {
        this.email = cpf;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
