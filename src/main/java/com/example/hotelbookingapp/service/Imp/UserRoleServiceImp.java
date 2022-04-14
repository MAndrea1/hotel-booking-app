package com.example.hotelbookingapp.service.Imp;

import com.example.hotelbookingapp.model.UserRole;
import com.example.hotelbookingapp.repository.UserRoleRepository;
import com.example.hotelbookingapp.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImp implements UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;
    
    @Override
    public List<UserRole> findAll() {
        return userRoleRepository.findAll();
    }
}
