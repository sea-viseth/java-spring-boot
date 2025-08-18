package org.springclass.springclassproject.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springclass.springclassproject.controller.request.AccountCreateRequest;
import org.springclass.springclassproject.controller.request.AccountUpdateRequest;
import org.springclass.springclassproject.respository.entity.AccountEntity;
import org.springclass.springclassproject.service.AccountService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountEntity> createAccount(@RequestBody @Valid AccountCreateRequest body) throws URISyntaxException {
        final var request = body.toAccountEntity();
        final var result = accountService.saveAccount(request);
        return ResponseEntity.created(new URI("/accounts")).body(result);
    }

    @GetMapping
    public ResponseEntity<Page<AccountEntity>> getAllAccounts(@RequestParam(value = "holderName", required = false) String holderName) {
        if (holderName != null && !holderName.isBlank()) {
            final var result = accountService.getAccounts(holderName);
            return ResponseEntity.ok(result);
        }
        final var result = accountService.findAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        final var result = accountService.findById(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAccount(@RequestBody @Valid AccountUpdateRequest body, @PathVariable Long id) {
        final var result = accountService.updateAccount(id, body);
        return ResponseEntity.ok(result);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }

}
