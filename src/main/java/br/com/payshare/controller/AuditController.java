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

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
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
}
