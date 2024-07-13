package com.devstack.ecom.upscale.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Data
public class UsernameAndPasswordAuthenticationRequest {
    private String username;
    private String password;
}
