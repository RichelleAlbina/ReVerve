package com.reverve.reverve.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reverve.reverve.domain.Login;
import com.reverve.reverve.repository.LoginRepo;

@Service
public class LoginService {
@Autowired
private LoginRepo repo;
public Login login(String username, String password){
    Login user1=repo.FindByUsernameAndPassword(username, password);
    return user1;
}
}

