package com.rzjaffery.hms.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterRequest {
    private String email;
    private String password;
    private String confirmPassword;
    private String role;
    private boolean termsAccepted;

}
