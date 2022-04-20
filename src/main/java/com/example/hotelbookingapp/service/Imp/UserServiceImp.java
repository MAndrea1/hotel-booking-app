package com.example.hotelbookingapp.service.Imp;


import com.example.hotelbookingapp.dto.SignUpUser;
import com.example.hotelbookingapp.dto.UpdateUserDto;
import com.example.hotelbookingapp.model.*;
import com.example.hotelbookingapp.repository.UserRepository;
import com.example.hotelbookingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Integer id) throws Exception {
        return userRepository.findById(id);
    }

    @Override
    public User save(UpdateUserDto entity) throws Exception {
        return null;
    }

    @Override
    public Optional<User> findByUserEmail(String userEmail){
        return userRepository.findByUserEmail(userEmail);
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        return userRepository.findByUserId(Integer.parseInt(userId));
    }

    @Override
    public Boolean existsByEmail(String userEmail) {
        return userRepository.existsByUserEmail(userEmail);
    }

    @Override
    public List<User> findAllByRole(Integer role) {
        return userRepository.findAllByRole(role);
    }

    @Override
    public List<User> findAllExceptRole(Integer role) {
        return userRepository.findAllExceptRole(role);
    }

    @Override
    public User save(SignUpUser signUpUser) throws Exception {
        try{
            User newUser = new User();
            newUser.setUserEmail(signUpUser.getEmail());
            newUser.setUserPassword(signUpUser.getPassword());
            newUser.setFkUserrole(getrole(signUpUser));
            userRepository.save(newUser);
            return newUser;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    private UserRole getrole(SignUpUser signUpUser) {
        String role = Role.USER.toString();
        if (signUpUser.getRole().equals(2)) {
            role = Role.ADMIN.toString();
        } else if (signUpUser.getRole().equals(1)) {
            role = Role.SUPERADMIN.toString();
        }
        return new UserRole(signUpUser.getRole(), role);
    }

    @Override
    public User save(User user) throws Exception {
        try{
            user = userRepository.save(user);
            return user;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Boolean delete(Integer id) throws Exception {
        try{
            if(userRepository.existsById(id)){
                userRepository.deleteById(id);
                return true;
            }else{
                throw new Exception();
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public User update(Integer id, UpdateUserDto user) throws Exception {
        try{
            if(user == null){
                throw new Exception();
            }
            if(!userRepository.existsById(id)){
                throw new Exception();
            }
            User updatedUser = userRepository.getById(id);
            if (!user.getEmail().isEmpty()) {
                updatedUser.setUserEmail(user.getEmail());
            }
            if (!user.getPassword().isEmpty()) {
                updatedUser.setUserPassword(user.getPassword());
            }
            return userRepository.getById(id);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
