package com.bach.springsecurityjwt.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bach.springsecurityjwt.models.LoginRequest;

@Service
@Transactional
public class UserService implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//Kiểm tra xem user có tồn tại trong database Không?
		LoginRequest user = userRepository.findByUsername(username);
		
		if(user == null) {
			throw new UsernameNotFoundException(username);
		}
		return new CustomUserDetails(user);
	}
}
