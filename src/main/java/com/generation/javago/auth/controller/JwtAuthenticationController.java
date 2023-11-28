package com.generation.javago.auth.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.generation.javago.auth.config.JwtTokenUtil;
import com.generation.javago.auth.model.JwtRequest;
import com.generation.javago.auth.model.JwtResponse;
import com.generation.javago.auth.model.UserInDb;
import com.generation.javago.auth.service.UserRepository;
import com.generation.javago.controller.util.InvalidEntityException;



@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private UserRepository repo;

	
	
	@Autowired
	private UserDetailsService jwtInMemoryUserDetailsService;

	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
			throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = jwtInMemoryUserDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);
		
		final Integer user_id = repo.findByUsername(authenticationRequest.getUsername()).get().getId();
		
		final boolean employed = repo.findByUsername(authenticationRequest.getUsername()).get().isEmployed();
		
		return ResponseEntity.ok(new JwtResponse(token,user_id,employed)) ;
	}
	
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody UserInDb user) throws Exception
	{
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));//criptando la password
		
		if(!user.isValid())
			throw new NoSuchElementException("One or more fields are invalid"+"\nErrors :\n"+user.getErrors());
		
		for(UserInDb u : repo.findAll())
		{
			if(u.getUsername() == user.getUsername())
				throw new InvalidEntityException("Username already present in our database");
		}
		repo.save(user);
		
		UserDetails userDetails = jwtInMemoryUserDetailsService
				.loadUserByUsername(user.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token,user.getId(),user.isEmployed()));
	}

	private void authenticate(String username, String password) throws Exception {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
