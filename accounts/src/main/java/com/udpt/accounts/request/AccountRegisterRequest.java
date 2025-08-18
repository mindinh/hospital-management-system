package com.udpt.accounts.request;

public record AccountRegisterRequest(
        String email,
        String password
) {
}
