package br.com.payshare.controller;

import br.com.payshare.api.AuditApiController;
import br.com.payshare.model.Audit;
import br.com.payshare.model.Lobby;
import br.com.payshare.model.UserPf;
import br.com.payshare.service.AuditService;
import br.com.payshare.service.LobbyService;
import br.com.payshare.service.UserPfService;
import br.com.payshare.utils.structure.FilaObj;
import org.apache.tomcat.jni.Local;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
public class AuditController implements AuditApiController, Observer {

    LobbyService lobbyService;
    UserPfService userPfService;
    AuditService auditService;

    LobbyController lobbyController;

    public AuditController() {
    }

    @Autowired
    public AuditController(LobbyService lobbyService, UserPfService userPfService, AuditService auditService, LobbyController lobbyController) {
        this.lobbyService = lobbyService;
        this.userPfService = userPfService;
        this.auditService = auditService;
        this.lobbyController = lobbyController;
    }

    @Override
    public ResponseEntity<List<Audit>> findAll() {
        if (auditService.findAll().isEmpty())
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(auditService.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity downloadCSV() {
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

        headers.add("Content-Disposition", "attachment; filename=" + nomeArquivo);
        headers.add("Content-Type", "text/csv");

        try {
            arq = new FileWriter(dirPath + nomeArquivo, true);
            saida = new Formatter(arq);
        } catch (IOException e) {
            System.err.println("Erro ao abrir arquivo");
        }

        try {
            saida.format("%s;%s;%s;%s;%s\n", auditId, amount, members, createdAt, updatedAt);
            try {
                DecimalFormat df = new DecimalFormat();
                df.setMaximumFractionDigits(2);
                df.setMinimumFractionDigits(0);
                df.setGroupingUsed(false);
                for (Audit a : auditData) {
//                    String valor = df.format(a.getAmountTransacted()).replace(",", ".");
//                    saida.format("%s;%d;%s;%s;%s\n", a.getId(),a.getActivedMembers(), valor, a.getCreatedAt().toString(), a.getUpdatedAt());
                }
            } catch (Exception e) {
                System.out.println("Erro " + e.getMessage());
            }
        } catch (FormatterClosedException e) {
            System.err.println("Erro ao gravar no arquivo");
        } finally {
            saida.close();
            try {
                arq.close();
            } catch (IOException e) {
                System.err.println("Erro ao fechar o arquivo");
            }
        }
        return new ResponseEntity(new FileSystemResource(dirPath + nomeArquivo), headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity downloadTxt() {
        List<Audit> auditData = auditService.findAll();
        String dirPath = "src\\main\\resources\\static\\";
        String nomeArquivo = "audit.txt";

        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Disposition", "attachment; filename=" + nomeArquivo);
        headers.add("Content-Type", "text/txt");

        String header = "";
        String corpo = "";
        String trailer = "";
        int contRegDados = 0;

        Date dataDeHoje = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        header += String.format("%-3s", "00");
        header += String.format("%-10s", "IDLOBBY");
        header += String.format("%-10s", "IDUSER");
        header += String.format("%-110s", "USERPF_IDS_LOBBY");
        header += String.format("%-200s", "LOBBYDATA");
        header += String.format("%-100s", "DESCRIPTIONHISTORY");
        header += String.format("%-15s", "CREATED_AT");
        header += String.format("%-15s", "TXT_CRIADO_EM:    ");
        header += formatter.format(dataDeHoje);
        header += " 01";

        gravaRegistro(nomeArquivo, header);

        for (Audit a : auditData) {
            corpo = "";
            corpo += String.format("%-3s", "99");
            corpo += String.format("%-10d", a.getIdLobby());
            corpo += String.format("%-10d", a.getIdUser());
            corpo += String.format("%-110s", a.getIdUserPfHistory());
            corpo += String.format("%-200s", a.getLobbyData());
            corpo += String.format("%-100s", a.getDescriptionHistory());
            corpo += String.format("%-30s", a.getCreatedAt());
            contRegDados++;
            gravaRegistro(nomeArquivo, corpo);
        }

        trailer += String.format("%-3s", "01");
        trailer += String.format("%010d", contRegDados);
        gravaRegistro(nomeArquivo, trailer);

        return new ResponseEntity(new FileSystemResource(dirPath + nomeArquivo), headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> uploadTxt(@RequestParam MultipartFile arquivo) {
        try {
            String setIdLobby;
            String setIdUser;
            String setIdUserPfHistory;
            String setLobbyData;
            String setDescriptionHistory;
            String setCreatedAt;

            BufferedReader reader = new BufferedReader(new InputStreamReader(arquivo.getInputStream()));
            String registro = reader.readLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            while (registro != null) {
                String indice = registro.substring(0, 3);
                if (indice.equals("99 ")) {
                    Audit audit = new Audit();

                    setIdLobby = registro.substring(3, 13);
                    setIdUser = registro.substring(13, 23);
                    setIdUserPfHistory = registro.substring(23, 131);
                    setLobbyData = registro.substring(131, 336);
                    setDescriptionHistory = registro.substring(330, 438);
                    setCreatedAt = registro.substring(433, 456);

                    audit.setIdLobby(Long.parseLong(setIdLobby.trim()));
                    audit.setIdUser(Long.parseLong(setIdUser.trim()));
                    audit.setIdUserPfHistory(setIdUserPfHistory.trim());
                    audit.setLobbyData(setLobbyData.trim());
                    audit.setDescriptionHistory(setDescriptionHistory.trim());
                    audit.setCreatedAt(LocalDateTime.parse(setCreatedAt.trim()));
                    lobbyController.pilhaLobby.push(audit);
                }
                registro = reader.readLine();
            }
            return ResponseEntity.created(null).build();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.badRequest().build();
    }

    public static void gravaRegistro(String nomeArquivo, String registro) {
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
            if (arg instanceof Lobby) {
                Lobby lobby = (Lobby) arg;
                lobbyController = (LobbyController) o;
                lobbyController.auditService.save(generateAudit(lobby));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private Audit generateAudit(Lobby l) throws Exception {
        Audit audit = new Audit();
        try {
            audit.setIdLobby(l.getId());
            audit.setIdUserPfHistory(generateJsonIds(l.getUserPfList()));
            for (UserPf pf : l.getUserPfList()) {
                audit.setIdUser(pf.getUserId());
            }
            audit.setLobbyData(generateJsonLobbyData(l));
            audit.setDescriptionHistory(l.getLobbyDescription());
        } catch (Exception e) {
            System.out.println("Error at AuditGenerated: " + e.getMessage());
        }
        return audit;
    }

    private String generateJsonIds(List<UserPf> list) throws Exception {
        JSONObject json = new JSONObject();
        JSONArray ids = new JSONArray();
        List<Integer> idsUser = new ArrayList<>();
        try {
            for (UserPf pf : list) {
                idsUser.add(Integer.parseInt(String.valueOf(pf.getUserId())));
            }
            for (Integer i : idsUser) {
                ids.put(i);
            }
            json.put("idsFromLobby", ids);
        } catch (Exception e) {
            System.out.println("Error at JsonGenerated: " + e.getMessage());
        }

        return json.toString();
    }

    private String generateJsonLobbyData(Lobby lobby) throws Exception {
        JSONObject json = new JSONObject();
        try {
            json.put("amountTotal", lobby.getAmountTotal());
            json.put("amountCreation", lobby.getAmount());
            json.put("createdAt", lobby.getCreationDate());
            json.put("expirationDate", lobby.getExpirationDate());
            json.put("lobbyDesc", lobby.getLobbyDescription());
            json.put("lobbyId", lobby.getId());
        } catch (Exception e) {
            System.out.println("Error at JsonGenerated: " + e.getMessage());
        }

        return json.toString();
    }


}
