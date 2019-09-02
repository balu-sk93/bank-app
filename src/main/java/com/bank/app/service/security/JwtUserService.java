package com.bank.app.service.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bank.app.data.repo.UserDetailsRepo;

@Service
public class JwtUserService implements UserDetailsService {

	@Autowired
	UserDetailsRepo userDetails;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		com.bank.app.data.dto.UserDetails user = userDetails.findByUserName(username);
		if (user != null) {
			return new User(username, user.getPassword(), new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

}
