package com.sirdave.lendingplatform.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    @Query("SELECT a FROM Account a WHERE a.user.phoneNumber = ?1")
    Optional<Account> findAccountByNumber(String phoneNumber);
}
