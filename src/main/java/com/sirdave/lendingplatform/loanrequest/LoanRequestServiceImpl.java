package com.sirdave.lendingplatform.loanrequest;

import com.sirdave.lendingplatform.account.Account;
import com.sirdave.lendingplatform.loanproduct.LoanProduct;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.NoResultException;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class LoanRequestServiceImpl implements LoanRequestService {

    @Autowired
    private final LoanRequestRepository repository;

    @Override
    public LoanRequest requestLoan(Account account, LoanProduct loanProduct) {
        LoanRequest request = new LoanRequest(account, loanProduct, LocalDateTime.now());
        return repository.save(request);
    }

    @Override
    public LoanRequest findRequestById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoResultException("Loan request with id " + id + " could not be found"));
    }
}
