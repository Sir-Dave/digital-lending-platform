package com.sirdave.lendingplatform.loanproduct;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class LoanConfig {

    @Bean
    CommandLineRunner commandLineRunner(LoanProductService loanProductService){
        return args -> {

            LoanProduct productA = new LoanProduct("Product A",
                    10000, 0.1, 15);

            LoanProduct productB= new LoanProduct("Product B",
                    25000, 0.125, 30
            );

            List<LoanProduct> loanProductList = loanProductService.getAllProducts();

            if (loanProductList.isEmpty()){
                loanProductService.saveProduct(productA);
                loanProductService.saveProduct(productB);
            }
        };
    }
}
