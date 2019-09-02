package com.bank.app.data.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.app.data.dto.Branches;

@Repository
public interface BranchRepo extends JpaRepository<Branches, String>{
	
	Branches findByIfsc(String ifsc);

	Page<Branches> findByIfsc(String ifscCode, Pageable pageable);

}
