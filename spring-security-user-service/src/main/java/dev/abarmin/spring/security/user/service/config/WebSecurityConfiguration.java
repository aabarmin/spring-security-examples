package dev.abarmin.spring.security.user.service.config;

import dev.abarmin.spring.security.user.service.user.RegisteredUser;
import dev.abarmin.spring.security.user.service.user.RegisteredUserAuthority;
import dev.abarmin.spring.security.user.service.user.RegisteredUserRepository;
import java.util.Map;
import java.util.Set;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

/**
 * @author Aleksandr Barmin
 */
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new DelegatingPasswordEncoder("bcrypt", Map.of(
        "bcrypt", new BCryptPasswordEncoder()
    ));
  }

  /**
   * Making H2 Console available, moving it out of protection of Spring Security.
   * WebSecurity allows excluding endpoints from Spring Security at all, global security.
   *
   * @param web
   * @throws Exception
   */
  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring()
        .antMatchers("/h2-console/**");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
          .anyRequest()
          .authenticated()
        .and()
          .formLogin();
  }

  @Bean
  public UserDetailsService jdbcUserDetailsService(DataSource dataSource,
                                                   RegisteredUserRepository userRepository,
                                                   PasswordEncoder encoder) {
    userRepository.findById("user")
        .orElseGet(() -> userRepository.save(RegisteredUser.builder()
                .userName("user")
                .password(encoder.encode("password"))
                .version(0) // to consider entity as new
                .enabled(true)
                .authorities(Set.of(
                    RegisteredUserAuthority.builder()
                        .authority("some authority")
                        .build()
                ))
            .build()));

    return new JdbcUserDetailsManager(dataSource);
  }
}
