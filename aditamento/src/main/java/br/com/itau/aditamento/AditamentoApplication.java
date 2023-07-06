package br.com.itau.aditamento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AditamentoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AditamentoApplication.class, args);
    }

}
