package com.sirdave.lendingplatform.loanrequest;

import com.sirdave.lendingplatform.account.Account;
import com.sirdave.lendingplatform.loanproduct.LoanProduct;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "loan_requests")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class LoanRequest {

    @Id
    @Setter(AccessLevel.PROTECTED)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "request_sequence")
    @SequenceGenerator(name = "request_sequence", sequenceName = "request_sequence", allocationSize = 1)
    @Column(nullable = false, updatable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "loanProduct_id")
    private LoanProduct loanProduct;

    LocalDateTime requestedAt;

    public LoanRequest(Account account, LoanProduct loanProduct, LocalDateTime requestedAt) {
        this.account = account;
        this.loanProduct = loanProduct;
        this.requestedAt = requestedAt;
    }
}
