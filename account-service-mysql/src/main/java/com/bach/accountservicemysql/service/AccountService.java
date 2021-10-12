package com.bach.accountservicemysql.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bach.accountservicemysql.entity.Account;
import com.bach.accountservicemysql.model.AccountDTO;
import com.bach.accountservicemysql.repository.AccountRepository;

public interface AccountService {

	void add(AccountDTO accountDTO);
	
	void update(AccountDTO accountDTO);
	
	void delete(Long id);
	
	void updatePassword(AccountDTO accountDTO);
	
	List<AccountDTO> getAll();
	
	AccountDTO getOne(Long id);
	
} 

@Service
@Transactional
class AccountServiceImpl implements AccountService{
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	ModelMapper modelMapper;

	@Override
	public void add(AccountDTO accountDTO) {
		Account account = modelMapper.map(accountDTO, Account.class);
		
		//account.setPassword(new BCryptPasswordEncoder().endcode(accounDTO.getPassword()));
		
		accountRepository.save(account);
		
		accountDTO.setId(account.getId());
		
	}

	@Override
	public void update(AccountDTO accountDTO) {
		
		Account account = accountRepository.getById(accountDTO.getId());
		
		if(account != null) {
			modelMapper.typeMap(AccountDTO.class, Account.class)
					.addMappings(mapper -> mapper.skip(Account::setPassword)).map(accountDTO, account);
			
			accountRepository.save(account);
		}
		
	}

	@Override
	public void updatePassword(AccountDTO accountDTO) {
		
		Account account = accountRepository.getById(accountDTO.getId());
		if(account != null) {
			//account.setPassword(new BCryptPasswordEncoder().endcode(accounDTO.getPassword()));
			accountRepository.save(account);
		}

	}

	@Override
	public void delete(Long id) {
		Account account = accountRepository.getById(id);
		
		if(account != null) {
			accountRepository.delete(account);
		}
		
	}

	@Override
	public List<AccountDTO> getAll() {
			
		List<AccountDTO> accountDTOs = new ArrayList<>();
		
		accountRepository.findAll().forEach((account) -> {
			accountDTOs.add(modelMapper.map(account, AccountDTO.class));
		});
		
		return accountDTOs;
	}

	@Override
	public AccountDTO getOne(Long id) {
		Account account = accountRepository.getById(id);
		
		if(account != null) {
			return modelMapper.map(account, AccountDTO.class);
		}
		
		return null;
	}

	
}
