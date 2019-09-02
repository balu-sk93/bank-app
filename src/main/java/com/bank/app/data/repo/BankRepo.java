package com.bank.app.data.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.app.data.dto.Banks;

@Repository
public interface BankRepo extends JpaRepository<Banks, Long>{

}
