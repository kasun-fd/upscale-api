package com.devstack.ecom.upscale.service.impl;

import com.devstack.ecom.upscale.auth.ApplicationUser;
import com.devstack.ecom.upscale.entity.User;
import com.devstack.ecom.upscale.entity.UserRoleHasUser;
import com.devstack.ecom.upscale.exception.EntryNotFoundException;
import com.devstack.ecom.upscale.repo.UserRepo;
import com.devstack.ecom.upscale.repo.UserRoleHasUserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.devstack.ecom.upscale.security.ApplicationUserRole.*;

@Service
@RequiredArgsConstructor
public class ApplicationUserServiceImpl implements UserDetailsService {

    private final UserRepo userRepo;
    private final UserRoleHasUserRepo userRoleHasUserRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> selectedUser = userRepo.findByEmail(username);
        if (selectedUser.isEmpty()){
            throw new EntryNotFoundException(String.format("Username %s not found!",username));
        }
        List<UserRoleHasUser> roleList = userRoleHasUserRepo.findAllByUser(selectedUser.get().getUserId());
        Set<SimpleGrantedAuthority> grantedAuthorities = new HashSet<>();
        for (UserRoleHasUser u:roleList){
            if (u.getUserRole().getRoleName().equals("USER")){
                grantedAuthorities.addAll(USER.grantedAuthoritySet());
            }
            if (u.getUserRole().getRoleName().equals("CUSTOMER")){
                grantedAuthorities.addAll(CUSTOMER.grantedAuthoritySet());
            }
            if (u.getUserRole().getRoleName().equals("ADMIN")){
                grantedAuthorities.addAll(ADMIN.grantedAuthoritySet());
            }
            if (u.getUserRole().getRoleName().equals("MANAGER")){
                grantedAuthorities.addAll(MANAGER.grantedAuthoritySet());
            }
        }
        return new ApplicationUser(
                grantedAuthorities,
                selectedUser.get().getPassword(),
                selectedUser.get().getEmail(),
                selectedUser.get().isAccountNonExpired(),
                selectedUser.get().isAccountNonLocked(),
                selectedUser.get().isCredentialsNonExpired(),
                selectedUser.get().isEnabled()
        );
    }
}
