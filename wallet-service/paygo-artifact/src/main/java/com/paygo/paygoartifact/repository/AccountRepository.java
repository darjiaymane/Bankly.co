package com.paygo.paygoartifact.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paygo.paygoartifact.entity.Wallet;

@Repository
public interface AccountRepository extends JpaRepository< Wallet, Long> {
    Optional< Wallet >  findByCinClient( String cinClient);
}
