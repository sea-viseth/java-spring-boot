package org.springclass.springclassproject.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Value;

@Value
public class AccountUpdateRequest {
    @NotBlank
    String accountHolderName;
}
