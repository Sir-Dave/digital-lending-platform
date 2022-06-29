package com.sirdave.lendingplatform.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sirdave.lendingplatform.account.Account;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "customers")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class User {
    @Id @Setter(AccessLevel.PROTECTED)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @Column(nullable = false, updatable = false)
    private int id;

    private String firstname;
    private String lastname;
    private String email;
    private String phoneNumber;

    @JsonIgnore
    private String password;

    private LocalDate dateJoined;
    private String role;
    private String[] authorities;
    private boolean isActive;
    private boolean isNotLocked;
    private boolean isVerified;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Account account;

    public User(String firstname, String lastname, String email,
                String phoneNumber, String password, LocalDate dateJoined,
                String role, String[] authorities, boolean isActive,
                boolean isNotLocked, boolean isVerified){
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.dateJoined = dateJoined;
        this.role = role;
        this.authorities = authorities;
        this.isActive = isActive;
        this.isNotLocked = isNotLocked;
        this.isVerified = isVerified;
    }
}
