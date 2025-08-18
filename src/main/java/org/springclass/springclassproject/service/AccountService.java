package org.springclass.springclassproject.service;

import org.springclass.springclassproject.controller.request.AccountUpdateRequest;
import org.springclass.springclassproject.respository.entity.AccountEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AccountService {
    AccountEntity saveAccount(AccountEntity account);
    Page<AccountEntity> getAccounts(String holderName);
    Page<AccountEntity> findAll();
    AccountEntity findById(Long id);
    AccountEntity updateAccount(Long id, AccountUpdateRequest account);
    void deleteAccount(Long id);
}

