package com.devstack.ecom.upscale.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Table(name = "user_role")
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class UserRole {

    @Id
    @Column(name = "role_id", length = 80)
    private String roleId;

    @Column(name = "role_name", length = 100)
    private String roleName;

    @Column(name = "description", length = 100)
    private String description;

    @OneToMany(mappedBy = "userRole", fetch = FetchType.LAZY)
    private Set<UserRoleHasUser> userRoleHasUsers;

}
