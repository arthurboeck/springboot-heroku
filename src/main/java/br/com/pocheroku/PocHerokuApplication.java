package br.com.pocheroku;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PocHerokuApplication {

    public static void main(String[] args) {
        SpringApplication.run(PocHerokuApplication.class, args);
    }

}
