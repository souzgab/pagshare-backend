package br.com.payshare.dto;

public class LoginUserDto {

    private String cpf;
    private String password;

    public LoginUserDto(String cpf, String password) {
        this.cpf = cpf;
        this.password = password;
    }

    public String getCpf() {
        return cpf;
    }

    public String getPassword() {
        return password;
    }
}
