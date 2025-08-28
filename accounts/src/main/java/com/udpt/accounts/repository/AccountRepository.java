package com.udpt.accounts.repository;

import com.udpt.accounts.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, String> {
    Optional<AccountEntity> findByUserId(String id);
    Optional<AccountEntity> findByEmailAddress(String email);
    Optional<AccountEntity> findByMobileNo(String mobileNo);
}
