package com.udpt.accounts.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "taikhoan")
@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AccountEntity extends BaseEntity {

    @Id
    @Column(name = "ma_tai_khoan")
    private String userId;

    @Column(name = "email")
    private String emailAddress;

    @Column(name = "so_dt")
    private String mobileNo;

    @Column(name = "ten_nguoi_dung")
    private String username;

    @Column(name = "mat_khau")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Status status;

}
