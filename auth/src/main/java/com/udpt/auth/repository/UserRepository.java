package com.udpt.auth.repository;

import com.udpt.auth.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByMobileNo(String mobileNumber);
    Optional<UserEntity> findByEmailAddress(String email);
}
