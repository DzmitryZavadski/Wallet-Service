package com.inno.wallet;

import com.inno.wallet.model.UserAccount;
import com.inno.wallet.repository.WalletRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class WalletServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WalletServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(WalletRepository repository) {
        return args -> {
            UserAccount testUser = new UserAccount();
            testUser.setUserName("Dima");
            testUser.setBalance(new BigDecimal("1000.00"));
            repository.save(testUser);

            System.out.println("--- Test user created with ID: " + testUser.getId() + " ---");
        };
    }
}
