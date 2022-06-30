package com.sirdave.lendingplatform.loanproduct;

import java.util.List;

public interface LoanService {

    void saveProduct(LoanProduct product);

    List<LoanProduct> getAllProducts();
}
