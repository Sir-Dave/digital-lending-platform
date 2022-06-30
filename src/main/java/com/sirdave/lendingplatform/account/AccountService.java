package com.sirdave.lendingplatform.account;

import com.sirdave.lendingplatform.exception.AccountException;
import com.sirdave.lendingplatform.exception.AccountNotFoundException;
import com.sirdave.lendingplatform.loanproduct.LoanProduct;
import com.sirdave.lendingplatform.user.User;
import java.util.List;

public interface AccountService {

    Account findAccountById(int id) throws AccountNotFoundException, AccountException;

    void createNewAccount(User user);

    Account updateAccountDetails(int accountId, double amount, double maxLoanCredit)
            throws AccountNotFoundException, AccountException;

    Account findAccountByNumber(String phoneNumber) throws AccountNotFoundException, AccountException;

    List<LoanProduct> getLoanOffers(int accountId) throws AccountException, AccountNotFoundException;

}
