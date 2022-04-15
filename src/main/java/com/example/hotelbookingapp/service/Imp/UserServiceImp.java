package com.example.hotelbookingapp.service.Imp;


import com.example.hotelbookingapp.model.User;
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

    public User save(User user) throws Exception {
        try{
            user = userRepository.save(user);
            return user;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
