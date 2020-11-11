package br.com.payshare.controller;

import br.com.payshare.api.AuditApiController;
import br.com.payshare.model.Audit;
import br.com.payshare.model.Lobby;
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
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@RestController
public class AuditController implements AuditApiController, Observer {

    LobbyService lobbyService;
    UserPfService userPfService;
    AuditService auditService;

    public AuditController(){};

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
        String amount = "AMOUNT_TRANSACTED";
        String members = "ACTIVED_MEMBERS";
        String createdAt = "CREATED_AT";
        String updatedAt = "UPDATED_AT";

        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Disposition", "attachment; filename="+nomeArquivo);
        headers.add("Content-Type", "text/csv");

        try {
            arq = new FileWriter(dirPath + nomeArquivo, true);
            saida = new Formatter(arq);
        }
        catch (IOException e) {
            System.err.println("Erro ao abrir arquivo");
        }

        try{
            saida.format("%s;%s;%s;%s;%s\n",auditId,amount,members,createdAt,updatedAt);
            try{
                DecimalFormat df = new DecimalFormat();
                df.setMaximumFractionDigits(2);
                df.setMinimumFractionDigits(0);
                df.setGroupingUsed(false);
                for (Audit a : auditData){
                    String valor = df.format(a.getAmountTransacted()).replace(",", ".");
                    saida.format("%s;%d;%s;%s;%s\n", a.getId(),a.getActivedMembers(), valor, a.getCreatedAt().toString(), a.getUpdatedAt());
                }
            }catch (Exception e){
                System.out.println("Erro " + e.getMessage());
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

        header += String.format("%-3s", "00");
        header += String.format("%-5s", "ID");
        header += String.format("%-25s", "CREATED_AT");
        header += String.format("%-10s", "MEMBERS");
        header += String.format("%-10s", "AMOUNT");
        header += String.format("%-20s", "UPDATED_AT");
        header += String.format("%-15s", "TXT_CRIADO_EM:    ");
        header += formatter.format(dataDeHoje);
        header += " 01";

        gravaRegistro(nomeArquivo, header);

        for (Audit a : auditData){
            corpo = "";
            corpo += String.format("%-3s", "99");
            corpo += String.format("%-5d", a.getId());
            corpo += String.format("%-25s", a.getCreatedAt());
            corpo += String.format("%-10d", a.getActivedMembers());
            corpo += String.format("%-10s", a.getAmountTransacted().toString());
            corpo += String.format("%-20s", a.getUpdatedAt());
            contRegDados++;
            gravaRegistro(nomeArquivo,corpo);
        }

        trailer += String.format("%-3s", "01");
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

    @Override
    public void update(Observable o, Object arg) {
        try {
            if (arg instanceof Lobby){
                Lobby lobby = (Lobby) arg;
                LobbyController lobbyController = (LobbyController) o;
                Audit generatedAudit = generateAudit(lobby);
                lobbyController.auditService.save(generatedAudit);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Audit generateAudit(Lobby l) {
        Audit audit = new Audit();
        LocalDateTime now = LocalDateTime.now();
        audit.setAmountTransacted(l.getAmount());
        audit.setIdLobby(l.getId());
        audit.setActivedMembers(l.getUserPfList().size());
        audit.setCreatedAt(l.getCreationDate());
        audit.setUpdatedAt(now);
        return audit;
    }
}
