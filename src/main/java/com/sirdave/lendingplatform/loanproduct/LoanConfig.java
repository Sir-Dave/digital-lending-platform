package com.sirdave.lendingplatform.loanproduct;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoanConfig {

    @Bean
    CommandLineRunner commandLineRunner(LoanService loanService){
        return args -> {

            LoanProduct productA = new LoanProduct("Product A",
                    10000, 0.1, 15);

            LoanProduct productB= new LoanProduct("Product B",
                    25000, 0.125, 30
            );

            loanService.saveProduct(productA);
            loanService.saveProduct(productB);
        };
    }
}
