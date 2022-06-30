package com.sirdave.lendingplatform.loanproduct;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LoanProductRepository extends JpaRepository<LoanProduct, Integer> {

    @Query("SELECT l FROM LoanProduct l WHERE l.maxAmountAllowable <= :amount")
    List<LoanProduct> getLoanOffersByAmount(@Param("amount") double amount);
}
