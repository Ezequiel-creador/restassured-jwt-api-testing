package com.proyect.testing.api;

import com.proyect.testing.controller.UserController;
import com.proyect.testing.model.User;
import com.proyect.testing.security.JwtService;
import com.proyect.testing.service.UserDetailsServiceImpl;
import com.proyect.testing.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import java.util.List;
import java.util.Optional;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;


@WebMvcTest(UserController.class)

public class UsersApiTest {
@Autowired
private MockMvc mockMvc;

@MockBean
    private UserService userService;

@MockBean
    JwtService jwtService;

@MockBean
    UserDetailsServiceImpl userDetailsService;

@Test
@WithMockUser
void testGetUsers()throws Exception{

    User user = new User(1L,"Eze","1234");
    
    List <User> users = List.of(user);
    
    when(userService.getAllUsers()).thenReturn(users);

    mockMvc.perform(get("/api/users"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].username").value("Eze"))
        .andExpect(jsonPath("$[0].password").value("1234"));

} 

@Test
@WithMockUser
void testGetUsersListEmpty() throws Exception {
  when(userService.getAllUsers()).thenReturn(List.of());

  mockMvc.perform(get("/api/users"))
          .andExpect(status().isOk())
          .andExpect(content().json("[]"));

}

@Test
@WithMockUser
void testUserNotFound() throws Exception {
    when(userService.getByUser("Ezequiel")).thenReturn(Optional.empty());
    mockMvc.perform(get("/api/users/Ezequiel"))
            .andExpect(status().isNotFound());
} 



}
