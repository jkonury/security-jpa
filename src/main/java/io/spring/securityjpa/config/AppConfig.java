package io.spring.securityjpa.config;

import io.spring.securityjpa.user.UserRepository;
import io.spring.securityjpa.user.UserRole;
import io.spring.securityjpa.user.UserService;
import io.spring.securityjpa.user.User;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

  @Bean
  public ApplicationRunner applicationRunner() {
    return new ApplicationRunner() {

      @Autowired
      UserService userService;

      @Autowired
      UserRepository userRepository;

      @Override
      public void run(ApplicationArguments args) {

        final User admin = User.builder()
          .userName("admin")
          .password("admin")
          .active(true)
          .roles(new HashSet<>(Arrays.asList(UserRole.ADMIN, UserRole.USER)))
          .build();

        Optional<User> findAccount = userRepository.findByUserName(admin.getUserName());

        if (!findAccount.isPresent()) {
          userService.saveAccount(admin);
        }

        final User user = User.builder()
          .userName("user")
          .password("user")
          .active(true)
          .roles(new HashSet<>(Collections.singletonList(UserRole.USER)))
          .build();

        findAccount = userRepository.findByUserName(user.getUserName());

        if (!findAccount.isPresent()) {
          userService.saveAccount(user);
        }
      }
    };
  }
}
