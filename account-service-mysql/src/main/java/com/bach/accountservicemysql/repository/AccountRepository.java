package com.bach.accountservicemysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bach.accountservicemysql.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
