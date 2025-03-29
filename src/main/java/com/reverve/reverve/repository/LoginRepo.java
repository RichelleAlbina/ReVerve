package com.reverve.reverve.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reverve.reverve.domain.Login;

@Repository
public interface LoginRepo extends  JpaRepository<Login,String>{
    Login FindByUsernameAndPassword(String username,String password);

}
