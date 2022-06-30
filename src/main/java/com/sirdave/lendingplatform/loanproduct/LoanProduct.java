package com.sirdave.lendingplatform.loanproduct;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sirdave.lendingplatform.loanrequest.LoanRequest;
import lombok.*;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "loan_products")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class LoanProduct {

    @Id
    @Setter(AccessLevel.PROTECTED)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loan_sequence")
    @SequenceGenerator(name = "loan_sequence", sequenceName = "loan_sequence", allocationSize = 1)
    @Column(nullable = false, updatable = false)
    private int id;

    private String name;
    private double maxAmountAllowable;
    private double interest;
    private int duration;

    @JsonIgnore
    @OneToMany(mappedBy = "loanProduct")
    Set<LoanRequest> loanRequestSet;

    public LoanProduct(String name, double amount, double interest, int duration) {
        this.name = name;
        this.maxAmountAllowable = amount;
        this.interest = interest;
        this.duration = duration;
    }
}
