package com.bach.accountservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bach.accountservice.model.Account;

public interface AccountRepository extends MongoRepository<Account, Long>  {

}
