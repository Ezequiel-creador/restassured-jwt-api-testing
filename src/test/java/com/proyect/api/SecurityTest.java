package com.proyect.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import com.proyect.testing.TestingApplication;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = TestingApplication.class)
@AutoConfigureMockMvc
public class SecurityTest {

@Autowired
private MockMvc mockMvc;

@Test
void ingresoUsuarioValido() throws Exception {
   mockMvc.perform(formLogin()
            .user("admin")
            .password("1234"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/users"));
    
}

@Test
void ingresoUsuarioInvalido() throws Exception {
   mockMvc.perform(formLogin()
            .user("admin")
            .password("12345"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/login?error"));
    
}

}
