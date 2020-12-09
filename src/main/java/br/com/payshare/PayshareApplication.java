package br.com.payshare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PayshareApplication {

    public static final String VERSION = "v1";
    public static final String API_PREFIX = "/" + VERSION;

    public static void main(String[] args) {
        SpringApplication.run(PayshareApplication.class, args);
    }
}
