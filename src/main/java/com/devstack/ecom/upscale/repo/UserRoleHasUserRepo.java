package com.devstack.ecom.upscale.repo;

import com.devstack.ecom.upscale.entity.UserRoleHasUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@EnableJpaRepositories
public interface UserRoleHasUserRepo extends JpaRepository<UserRoleHasUser, String> {
    List<UserRoleHasUser> findAllByUser(String userId);
}
