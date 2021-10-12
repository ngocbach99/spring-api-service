package com.bach.springsecurityjwt.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bach.springsecurityjwt.dao.CustomUserDetails;
import com.bach.springsecurityjwt.dao.UserRepository;
import com.bach.springsecurityjwt.models.LoginRequest;
import com.bach.springsecurityjwt.models.LoginResponse;
import com.bach.springsecurityjwt.security.JwtTokenProvider;

@RestController
@RequestMapping("/api")
class HelloWorldController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@PostMapping("/login")
	public LoginResponse createAuthenticationToken(@Valid @RequestBody LoginRequest loginRequest) throws Exception {

			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
	                        loginRequest.getPassword())
			);
		
		//Nếu không xảy ra exception tức là thông tin hợp lệ.
		//Set thông tin authentication vào SecurityContext
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		// Trả về JWT cho người dùng
		String jwt = jwtTokenProvider.generateToken((CustomUserDetails)authentication.getPrincipal());
		
		return new LoginResponse(jwt);

	}

	@GetMapping("/random")
	public String randomStuff() {
		return "JWT hợp lệ mới thấy được message này";
	}
	
}