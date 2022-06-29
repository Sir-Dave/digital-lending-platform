package com.sirdave.lendingplatform.loanproduct;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements LoanService{

    @Autowired
    private final LoanRepository repository;

    @Override
    public void saveProduct(LoanProduct product) {
        repository.save(product);
    }
}
