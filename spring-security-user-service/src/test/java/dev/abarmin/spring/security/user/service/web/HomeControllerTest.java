package dev.abarmin.spring.security.user.service.web;

import dev.abarmin.spring.security.user.service.user.RegisteredUserRepository;
import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Aleksandr Barmin
 */
@WebMvcTest(HomeController.class)
class HomeControllerTest {
  @Autowired
  MockMvc mockMvc;

  @MockBean
  DataSource dataSource;

  @MockBean
  RegisteredUserRepository registeredUserRepository;

  @Test
  void redirected_ifNotAuthenticated() throws Exception {
    mockMvc.perform(get("/"))
        .andExpect(status().is3xxRedirection());
  }

  @Test
  @WithMockUser(username = "custom user name")
  void ok_ifAuthenticated() throws Exception {
    mockMvc.perform(get("/"))
        .andExpect(status().isOk())
        .andExpect(model().attribute("name", "custom user name"));
  }
}