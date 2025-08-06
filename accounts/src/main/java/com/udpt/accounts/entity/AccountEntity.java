package com.udpt.accounts.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "accounts")
@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AccountEntity extends BaseEntity {

    @Id
    @Column(name = "user_id", columnDefinition = "BINARY(16)")
    private UUID userId;

    @Column(name = "email")
    private String emailAddress;

    @Column(name = "mobile_number")
    private String mobileNo;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Status status;

}
