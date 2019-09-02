package com.bank.app.data.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.app.data.dto.BankView;

@Repository
public interface BankViewRepo extends JpaRepository<BankView, String> {

	List<BankView> findAllByBankNameAndCity(String bankName, String city);

	Page<BankView> findAllByBankNameAndCity(String bankName, String city, Pageable pageable);
}
