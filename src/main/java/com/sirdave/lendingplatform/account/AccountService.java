package com.sirdave.lendingplatform.account;

import com.sirdave.lendingplatform.exception.AccountNotFoundException;
import com.sirdave.lendingplatform.user.User;

public interface AccountService {

    Account findAccountById(int id) throws AccountNotFoundException;

    Account createNewAccount(User user);

    Account updateAccountDetails(int accountId, double amount, double maxLoanCredit)
            throws AccountNotFoundException;

    Account findAccountByNumber(String phoneNumber) throws AccountNotFoundException;

}
