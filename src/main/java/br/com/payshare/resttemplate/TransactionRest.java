package br.com.payshare.resttemplate;

import br.com.payshare.dto.TransactionDto;
import br.com.payshare.dto.TransactionWalletDto;
import br.com.payshare.model.Transaction;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

public class TransactionRest {
    private final String URL = "https://mercadopagoapi.herokuapp.com";
    RestTemplate restTemplate = new RestTemplateBuilder()
            .rootUri(URL)
            .build();

    public ResponseEntity<?> createTransaction(String token, long idUser, BigDecimal amount) {
        TransactionDto transactionDto = new TransactionDto();
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(transactionDto, createJSONHeader(token));
        ResponseEntity<TransactionDto> result = restTemplate.exchange("/pagar/" + idUser + "/" + amount, HttpMethod.GET,
                requestEntity, new ParameterizedTypeReference<TransactionDto>() {
                });

       // System.out.println(result);

        return new ResponseEntity<>(result.getBody(), HttpStatus.OK);
    }

    public ResponseEntity<?> createTransactionWallet(String token, long idUser, BigDecimal amount) {
        TransactionDto transactionDto = new TransactionDto();
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(transactionDto, createJSONHeader(token));
        ResponseEntity<TransactionWalletDto> result = restTemplate.exchange("/wallet/" + idUser + "/" + amount, HttpMethod.GET,
                requestEntity, new ParameterizedTypeReference<TransactionWalletDto>() {
                });

        // System.out.println(result);

        return new ResponseEntity<>(result.getBody(), HttpStatus.OK);
    }


    private static HttpHeaders createJSONHeader(String token) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setBearerAuth(token);
        return httpHeaders;
    }
}
