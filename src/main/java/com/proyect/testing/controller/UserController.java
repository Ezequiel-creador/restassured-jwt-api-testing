package com.proyect.testing.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.proyect.testing.model.User;
import com.proyect.testing.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "Users", description = "Operaciones CRUD de usuarios")
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
     
    @Operation(summary = "Obtener usuario por username")
    @GetMapping("/{username}")
    public ResponseEntity<?> getByUser(@PathVariable String username){
        Optional<User> user = userService.getByUser(username);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
     @Operation(summary = "Obtener lista de usuarios")
     @GetMapping
    public List <User> getAllUsers(){
        return userService.getAllUsers();
    }
    
    @Operation(summary = "Crear un nuevo usuario")
    @PostMapping("/create")
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }
 
    @Operation(summary = "Actualizar un usuario existente")
    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user){
        return userService.updateUser(id,user);
    }

    @Operation(summary = "Eliminar un usuario")
    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

   
}



