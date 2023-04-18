package com.example.mbonespring.interfaces;

import com.example.mbonespring.models.entities.UserEntity;
import com.example.mbonespring.models.entities.UserRoleEntity;

import java.util.List;

public interface UserService {

    UserEntity addNewUser (UserEntity userEntity);
    UserRoleEntity addNewRole (UserRoleEntity userRoleEntity);
    void addRoleToUser (String userName, String roleName);
    UserEntity loadUserByUserName(String userName);
    List<UserEntity> listUsers();
}