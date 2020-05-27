package com.spring_security_exmp.Address_Person.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.spring_security_exmp.Address_Person.config.SecurityConfig;
import com.spring_security_exmp.Address_Person.config.JwtUtil;
import com.spring_security_exmp.Address_Person.model.AuthenticationRequest;
import com.spring_security_exmp.Address_Person.model.AuthenticationResponse;
import com.spring_security_exmp.Address_Person.model.User;
import com.spring_security_exmp.Address_Person.repository.UserRepository;
import com.spring_security_exmp.Address_Person.service.MyUserDetailsService;

@RestController
@RequestMapping(value = "/api")
public class Authentication {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SecurityConfig securityConfig;
	
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		
		try {		
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(), authenticationRequest.getPassword())
			);
			
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}
		final UserDetails userDetails = myUserDetailsService
				.loadUserByUsername(authenticationRequest.getUserName());
		Optional<User> user = userRepository.findByUserName(authenticationRequest.getUserName());
		final String jwt = jwtTokenUtil.generateToken(userDetails, user.get());
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}

}