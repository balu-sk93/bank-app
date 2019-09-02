package com.bank.app.service.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bank.app.service.model.AuthRequest;
import com.bank.app.service.model.AuthResponse;
import com.bank.app.service.security.JwtTokenProvider;
import com.bank.app.service.security.JwtUserService;

@RestController
public class AuthenticateController {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthenticateController.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider jwtTokenUtil;

	@Autowired
	private JwtUserService userDetailsService;

	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authenticationRequest) throws Exception {

		String token = null;
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
					authenticationRequest.getPassword()));
			UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
			token = jwtTokenUtil.generateToken(userDetails);
		} catch (Exception e) {
			logger.error("Auth Failed : "+e.getMessage());
			e.printStackTrace();
			throw new BadCredentialsException("Authorization Failed", e);
		}
		return ResponseEntity.ok(new AuthResponse(token));
	}

}
