package com.sirdave.lendingplatform.loanproduct;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.List;

@Service
@AllArgsConstructor
public class LoanProductServiceImpl implements LoanProductService {

    @Autowired
    private final LoanProductRepository repository;

    @Override
    public void saveProduct(LoanProduct product) {
        repository.save(product);
    }

    @Override
    public LoanProduct findOneProductById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoResultException("No loan product with id " + id + " was found"));
    }

    @Override
    public List<LoanProduct> getAllProducts() {
        return repository.findAll();
    }

    @Override
    public List<LoanProduct> getLoanOffersByAmount(double amount) {
        return repository.getLoanOffersByAmount(amount);
    }
}
