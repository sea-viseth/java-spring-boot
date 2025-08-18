package org.springclass.springclassproject.service.imp;

import lombok.RequiredArgsConstructor;
import org.springclass.springclassproject.controller.request.AccountUpdateRequest;
import org.springclass.springclassproject.exception.ResourceNotFoundException;
import org.springclass.springclassproject.respository.AccountRepository;
import org.springclass.springclassproject.respository.entity.AccountEntity;
import org.springclass.springclassproject.service.AccountService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImp implements AccountService {
    private final AccountRepository accountRepository;

    @Override
    @Transactional
    public AccountEntity saveAccount(AccountEntity account) {
        return accountRepository.save(account);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AccountEntity> findAll() {
        final var pageable = PageRequest.of(0, 10);
        return accountRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public AccountEntity findById(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("account not found", "account with this id %d does not exist".formatted(id)));
    }

    @Override
    @Transactional
    public AccountEntity updateAccount(Long id, AccountUpdateRequest updateForm) {
        final var account = accountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("account not found", "account with this id %d does not exist".formatted(id)));
        account.updateEntity(updateForm);
        return accountRepository.save(account);
    }

    @Override
    @Transactional
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    public Page<AccountEntity> getAccounts(String holderName) {
        final var pageable = PageRequest.of(0, 10);
        final var result = accountRepository.getAccountEntityByAccountHolderName(holderName);
        if(result.isEmpty()){
            return Page.empty();
        }
        return new PageImpl<>(List.of(result.get()), pageable, 1);
    }
}
