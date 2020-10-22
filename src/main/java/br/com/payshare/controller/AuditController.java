package br.com.payshare.controller;

import br.com.payshare.api.AuditApiController;
import br.com.payshare.model.Audit;
import br.com.payshare.service.AuditService;
import br.com.payshare.service.LobbyService;
import br.com.payshare.service.UserPfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.List;

@RestController
public class AuditController implements AuditApiController {

    LobbyService lobbyService;
    UserPfService userPfService;
    AuditService auditService;

    @Autowired
    public AuditController(LobbyService lobbyService, UserPfService userPfService, AuditService auditService) {
        this.lobbyService = lobbyService;
        this.userPfService = userPfService;
        this.auditService = auditService;
    }

    @Override
    public ResponseEntity<List<Audit>> findAll(){
        if (auditService.findAll().isEmpty())
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(auditService.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity downloadCSV(){
        List<Audit> auditData = auditService.findAll();

        FileWriter arq = null;
        Formatter saida = null;
        String dirPath = "src\\main\\resources\\static\\";
        String nomeArquivo = "audit.csv";
        String auditId = "AUDIT_ID";
        String lobbyId = "LOBBY_ID";
        String amount = "AMOUNT_TRANSACTED";
        String members = "ACTIVED_MEMBERS";
        String createdAt = "CREATED_AT";

        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Disposition", "attachment; filename="+nomeArquivo);
        headers.add("Content-Type", "text/csv");

        try {
            arq = new FileWriter(dirPath + nomeArquivo, false);
            saida = new Formatter(arq);
        }
        catch (IOException e) {
            System.err.println("Erro ao abrir arquivo");
        }

        try{
            saida.format("%s;%s;%s;%s;%s;",auditId,lobbyId,amount,members,createdAt);
            for (Audit a : auditData){
                saida.format("%d;%d;%f;%d;%s\n",a.getId(),a.getLobbyId(),a.getAmountTransacted(),a.getActivedMembers(),a.getCreatedAt().toString());
            }
        }
        catch (FormatterClosedException e) {
            System.err.println("Erro ao gravar no arquivo");
        }
        finally {
            saida.close();
            try {
                arq.close();
            }
            catch (IOException e) {
                System.err.println("Erro ao fechar o arquivo");
            }
        }
        return new ResponseEntity(new FileSystemResource(dirPath+nomeArquivo),headers, HttpStatus.OK );
    }

    @Override
    public ResponseEntity downloadTxt(){
        List<Audit> auditData = auditService.findAll();
        String dirPath = "src\\main\\resources\\static\\";
        String nomeArquivo = "audit.txt";

        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Disposition", "attachment; filename="+nomeArquivo);
        headers.add("Content-Type", "text/txt");

        String header = "";
        String corpo = "";
        String trailer = "";
        int contRegDados = 0;

        Date dataDeHoje = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        header += "00_ID_CREATEDAT_ACTIVEDMEMBERS_AMOUNTTRANSACTED_LOBBYIDFK";
        header += formatter.format(dataDeHoje);
        header += "01";

        gravaRegistro(nomeArquivo, header);

        for (Audit a : auditData){
            corpo = "";
            corpo += "99";
            corpo += String.format("%-5d", a.getId());
            corpo += String.format("%-15s", a.getCreatedAt());
            corpo += String.format("%-5d", a.getActivedMembers());
            corpo += String.format("%-10d", a.getAmountTransacted());
            corpo += String.format("%-5d", a.getLobbyId());
            contRegDados++;
            gravaRegistro(nomeArquivo,corpo);
        }

        trailer += "01";
        trailer += String.format("%010d", contRegDados);
        gravaRegistro(nomeArquivo,trailer);

        return new ResponseEntity(new FileSystemResource(dirPath+nomeArquivo),headers, HttpStatus.OK );
    }

    public static void gravaRegistro (String nomeArquivo, String registro) {
        BufferedWriter saida = null;
        try {
            saida = new BufferedWriter(new FileWriter("src\\main\\resources\\static\\" + nomeArquivo, true));
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }

        try {
            saida.append(registro + "\n");
            saida.close();

        } catch (IOException e) {
            System.err.printf("Erro ao gravar arquivo: %s.\n", e.getMessage());
        }
    }
}
