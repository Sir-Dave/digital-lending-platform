package com.sirdave.lendingplatform.loanproduct;

import java.util.List;

public interface LoanProductService {

    void saveProduct(LoanProduct product);

    LoanProduct findOneProductById(int id);

    List<LoanProduct> getAllProducts();

    List<LoanProduct> getLoanOffersByAmount(double amount);
}
