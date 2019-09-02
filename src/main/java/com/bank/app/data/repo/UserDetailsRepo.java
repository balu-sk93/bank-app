package com.bank.app.data.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.app.data.dto.UserDetails;

@Repository
public interface UserDetailsRepo extends JpaRepository<UserDetails, Long> {

	UserDetails findByUserName(String userName);
	
}
