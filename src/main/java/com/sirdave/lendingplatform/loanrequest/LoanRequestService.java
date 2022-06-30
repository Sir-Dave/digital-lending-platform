package com.sirdave.lendingplatform.loanrequest;

import com.sirdave.lendingplatform.account.Account;
import com.sirdave.lendingplatform.loanproduct.LoanProduct;

public interface LoanRequestService {

    LoanRequest requestLoan(Account account, LoanProduct loanProduct);

    LoanRequest findRequestById(int id);
}
