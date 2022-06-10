package com.luladjiev.nocturnal;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
public class WebSecurityTest {

  @Autowired
  private WebApplicationContext context;

  private MockMvc mockMvc;

  @BeforeEach
  public void setup() {
    mockMvc =
      MockMvcBuilders
        .webAppContextSetup(context)
        .apply(springSecurity())
        .build();
  }

  @Test
  @WithMockUser(value = "admin", password = "123")
  void shouldLoginUser() throws Exception {
    mockMvc
      .perform(
        SecurityMockMvcRequestBuilders.formLogin().user("admin").password("123")
      )
      .andExpect(
        SecurityMockMvcResultMatchers.authenticated().withUsername("admin")
      );
  }

  @Test
  void shouldRequireAuthenticationForApiEndpoints() throws Exception {
    mockMvc.perform(get("/api/test")).andExpect(status().isUnauthorized());
  }

  @Test
  @WithMockUser
  void invalidApiEndpointShouldReturn404() throws Exception {
    mockMvc
      .perform(get("/api/invalid-url"))
      .andExpect(status().isNotFound())
      .andExpect(
        content()
          .string(
            "{\"message\":\"Resource not found\",\"status\":\"NOT_FOUND\",\"code\":404}"
          )
      );
  }
}
