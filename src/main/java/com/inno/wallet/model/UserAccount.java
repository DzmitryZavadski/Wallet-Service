package com.inno.wallet.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "user_accounts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAccount {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id;

        @Column(nullable = false)
        String userName;

        @Column(nullable = false)
        BigDecimal balance;
}
