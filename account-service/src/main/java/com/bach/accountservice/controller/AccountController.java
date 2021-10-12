package com.bach.accountservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bach.accountservice.model.Account;
import com.bach.accountservice.repository.AccountRepository;

@RestController
public class AccountController {

	@Autowired
	private AccountRepository accountRepository;
	
	@PostMapping("/addAccount")
	public String saveAccount(@RequestBody Account account) {
		accountRepository.save(account);
		
		return "Added account with id" + account.getId();
	}
	
	@GetMapping("/findAllAccounts")
	public List<Account> getAccounts(){
		return accountRepository.findAll();
	}
	
	@GetMapping("/findAllAccounts/{id}")
	public Optional<Account> getAccount(@PathVariable(name = "id") long id) {
		
		return accountRepository.findById(id);
	}
	
	@DeleteMapping("/deleteAccount/{id}")
	public String deleteAccount(@PathVariable(name = "id") long id) {
		accountRepository.deleteById(id);
		return "Account is deleted with id : " + id;
	}
}
