package com.example.hotelbookingapp.service.Imp;

import com.example.hotelbookingapp.model.UserRole;
import com.example.hotelbookingapp.repository.UserRoleRepository;
import com.example.hotelbookingapp.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserRoleServiceImp implements UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;
    
    @Override
    public List<UserRole> findAll() {
        return userRoleRepository.findAll();
    }

    @Override
    public Optional<UserRole> findById(Integer id) throws Exception {
        return Optional.empty();
    }

    @Override
    public UserRole save(UserRole entity) throws Exception {
        return null;
    }

    @Override
    public UserRole update(Integer id, UserRole Entity) throws Exception {
        return null;
    }

    @Override
    public Boolean delete(Integer id) throws Exception {
        return false;
    }
}
