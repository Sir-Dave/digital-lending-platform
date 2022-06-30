package com.sirdave.lendingplatform.account;

import com.sirdave.lendingplatform.exception.AccountException;
import com.sirdave.lendingplatform.exception.AccountNotFoundException;
import com.sirdave.lendingplatform.exception.MaxCreditExceededException;
import com.sirdave.lendingplatform.loanproduct.LoanProduct;
import com.sirdave.lendingplatform.loanproduct.LoanProductService;
import com.sirdave.lendingplatform.loanrequest.LoanRequest;
import com.sirdave.lendingplatform.loanrequest.LoanRequestService;
import com.sirdave.lendingplatform.user.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class AccountServiceImpl implements AccountService{

    @Autowired
    private final AccountRepository repository;
    private final LoanProductService loanProductService;
    private final LoanRequestService loanRequestService;

    @Override
    public Account findAccountById(int id) throws AccountNotFoundException, AccountException {
        Account account = repository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("No account with id " + id + " exists"));

        //Returns failed if amount is exactly 5000 else success for any other amount
        if (account.getCurrentAmount() == 5000)
            throw new AccountException("Failed");

        return account;
    }

    @Override
    public void createNewAccount(User user) {
        Account account = new Account(0, 10000, user);
        repository.save(account);
    }

    @Override
    public Account updateAccountDetails(int accountId, double amount, double maxLoanCredit) throws AccountNotFoundException, AccountException {
        Account account = findAccountById(accountId);
        if (amount >= 0)
            account.setCurrentAmount(amount);

        if (maxLoanCredit >= 0)
            account.setMaxLoanCredit(maxLoanCredit);

        return account;
    }

    @Override
    public Account findAccountByNumber(String phoneNumber) throws AccountNotFoundException, AccountException {
        Account account = repository.findAccountByNumber(phoneNumber)
                .orElseThrow(() -> new AccountNotFoundException("No account with number" + phoneNumber +
                        " was found"));

        //Returns failed if amount is exactly 5000 else success for any other amount
        if (account.getCurrentAmount() == 5000)
            throw new AccountException("Failed");

        return account;
    }

    @Override
    public List<LoanProduct> getLoanOffers(int accountId) throws AccountException, AccountNotFoundException {
        Account account = findAccountById(accountId);
        return loanProductService.getLoanOffersByAmount(account.getMaxLoanCredit());
    }

    @Transactional
    @Override
    public Account chooseLoanOffer(int loanProductId, int accountId) throws AccountException, AccountNotFoundException, MaxCreditExceededException {
        Account account = findAccountById(accountId);
        LoanProduct product = loanProductService.findOneProductById(loanProductId);

        // Get the new amount requested using the loan product
        double amount = account.getCurrentAmount();
        double credit = product.getMaxAmountAllowable();

        double newAmount = amount + credit;

        if (newAmount > account.getMaxLoanCredit())
            throw new MaxCreditExceededException("Cannot process loan, maximum credit limit has been exceeded");

        // else update the current amount in the account
        loanRequestService.requestLoan(account, product);

        account.setCurrentAmount(newAmount);
        return account;
    }
}
