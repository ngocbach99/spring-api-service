package com.bach.springsecurityjwt.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bach.springsecurityjwt.models.LoginRequest;

@Repository
public interface UserRepository extends JpaRepository<LoginRequest, Long> {

	@Query("SELECT u FROM LoginRequest u WHERE u.username = :username")
	public LoginRequest findByUsername(@Param("username") String username);
	
}
