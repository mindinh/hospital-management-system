package com.udpt.accounts.request;

public record AccountRegisterRequest(
        String email,
        String password,

        String hoTen,
        String soDT,
        String ngaySinh,
        String gioiTinh
) {
}
