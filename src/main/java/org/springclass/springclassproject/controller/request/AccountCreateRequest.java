package org.springclass.springclassproject.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Value;
import org.springclass.springclassproject.respository.entity.AccountEntity;

@Value
public class AccountCreateRequest {
    @NotBlank
    @Size(min = 2, max = 7, message = "account number must be at least 2-7 character")
    String accountNumber;
    @NotBlank
    String accountHolderName;
    @NotBlank
    String accountType;

    public AccountEntity toAccountEntity() {
        AccountEntity account = new AccountEntity();
        account.setAccountNumber(accountNumber);
        account.setAccountHolderName(accountHolderName);
        account.setAccountType(accountType);
        return account;
    }
}
