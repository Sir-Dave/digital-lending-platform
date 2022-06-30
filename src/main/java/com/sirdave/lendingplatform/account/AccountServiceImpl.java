package com.sirdave.lendingplatform.account;

import com.sirdave.lendingplatform.exception.AccountNotFoundException;
import com.sirdave.lendingplatform.user.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class AccountServiceImpl implements AccountService{

    @Autowired
    private final AccountRepository repository;

    @Override
    public Account findAccountById(int id) throws AccountNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("No account with id " + id + " exists"));
    }

    @Override
    public Account createNewAccount(User user) {
        Account account = new Account(0, 10000, user);
        return repository.save(account);
    }

    @Override
    public Account updateAccountDetails(int accountId, double amount, double maxLoanCredit) throws AccountNotFoundException {
        Account account = findAccountById(accountId);
        if (amount >= 0)
            account.setCurrentAmount(amount);

        if (maxLoanCredit >= 0)
            account.setMaxLoanCredit(maxLoanCredit);

        return account;
    }

    @Override
    public Account findAccountByNumber(String phoneNumber) throws AccountNotFoundException {
        return repository.findAccountByNumber(phoneNumber)
                .orElseThrow(() -> new AccountNotFoundException("No account with number" + phoneNumber +
                        " was found"));
    }
}
