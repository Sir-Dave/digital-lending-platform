package com.sirdave.lendingplatform.account;

import com.sirdave.lendingplatform.exception.AccountException;
import com.sirdave.lendingplatform.exception.AccountNotFoundException;
import com.sirdave.lendingplatform.loanproduct.LoanProduct;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = {"/account"})
@AllArgsConstructor
public class AccountController {

    @Autowired
    private final AccountService accountService;

    @GetMapping
    public ResponseEntity<Account> getAccount(@RequestParam String accountNumber) throws AccountNotFoundException, AccountException {
        Account account = accountService.findAccountByNumber(accountNumber);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @GetMapping("/{id}/loan-offers")
    public ResponseEntity<List<LoanProduct>> getLoanOffers(@PathVariable("id") int id) throws AccountException, AccountNotFoundException {
        List<LoanProduct> products = accountService.getLoanOffers(id);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }


}
