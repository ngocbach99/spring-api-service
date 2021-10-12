package com.bach.springsecurityjwt.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bach.springsecurityjwt.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	 User findByUsername(String username);
	
}
