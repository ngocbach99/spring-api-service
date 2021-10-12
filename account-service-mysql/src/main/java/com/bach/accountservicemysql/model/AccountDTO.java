package com.bach.accountservicemysql.model;

import java.util.Set;

import lombok.Data;

@Data
public class AccountDTO {

	private Long id;
	
	private String name;
	
	private String username;
	
	private String password;
	
	private Set<String> roles;
	
}
