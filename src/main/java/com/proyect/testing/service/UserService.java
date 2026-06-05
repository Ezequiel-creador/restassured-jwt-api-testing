package com.proyect.testing.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.proyect.testing.model.User;
import com.proyect.testing.repository.UserRepository;

@Service
public class UserService {
@Autowired
private UserRepository uRepository;
@Autowired
private PasswordEncoder passwordEncoder;

public List<User> getAllUsers(){
    return uRepository.findAll();
}

public Optional<User> getByUser(String username){
    return uRepository.findByUsername(username);
}

public User updateUser(Long id, User dataUser){
    User user = uRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    user.setUsername(dataUser.getUsername());
    if (dataUser.getPassword() != null && !dataUser.getPassword().isEmpty()) {
        user.setPassword(passwordEncoder.encode(dataUser.getPassword()));
    }
    System.out.println("usuario actualizado");
    return uRepository.save(user);
    } 

public User getById(Long id) {
    return uRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
    
public User createUser(User user){
  user.setPassword(passwordEncoder.encode(user.getPassword()));
  return uRepository.save(user);
    } 

public void deleteUser(Long id){
    uRepository.deleteById(id);
    System.out.println("usuario eliminado");
    }
}
