package com.example.ass.repository;

import com.example.ass.entity.UserActivation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserActivationRepository extends JpaRepository<UserActivation, Long> {
    UserActivation findByActivationCode(String activationCode);
}