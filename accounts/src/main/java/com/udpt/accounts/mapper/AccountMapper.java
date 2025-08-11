package com.udpt.accounts.mapper;

import com.udpt.accounts.dto.AccountDto;
import com.udpt.accounts.entity.AccountEntity;

public class AccountMapper {
    private AccountMapper() {}

    public static AccountDto mapToAccountDto(AccountEntity accountEntity, AccountDto accountDto) {
        accountDto.setEmail(accountEntity.getEmailAddress());
        accountDto.setSoDienThoai(accountEntity.getMobileNo());
        accountDto.setTenNguoiDung(accountEntity.getUsername());

        return accountDto;
    }

    public static AccountEntity mapToAccountEntity(AccountDto accountDto, AccountEntity accountEntity) {
        accountEntity.setEmailAddress(accountDto.getEmail());
        accountEntity.setMobileNo(accountDto.getSoDienThoai());
        accountEntity.setUsername(accountDto.getTenNguoiDung());


        return accountEntity;
    }
}
