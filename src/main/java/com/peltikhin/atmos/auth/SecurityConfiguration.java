package com.peltikhin.atmos.auth;

import com.peltikhin.atmos.jpa.repositories.UserRepository;
import com.peltikhin.atmos.services.models.CurrentUserInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.context.annotation.RequestScope;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private static void onSuccess(HttpServletRequest request,
                                  HttpServletResponse response,
                                  Authentication authentication) throws ServletException, IOException {
        request.getRequestDispatcher("/auth/check").forward(request, response);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> {
            var result = userRepository.findByUsername(username);
            if (result.isEmpty())
                throw new UsernameNotFoundException("User with username \"" + username + "\" not found");
            return new AuthUser(result.get());
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/auth/*").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/auth/login")
                .successHandler(SecurityConfiguration::onSuccess)
                .and()
                .logout()
                .logoutUrl("/auth/logout")
                .logoutSuccessHandler(SecurityConfiguration::onSuccess)
                .and()
                .csrf().disable();
        return http.build();
    }

    @Bean
    @RequestScope
    public CurrentUserInfo currentUserInfo() {
        var authorization = SecurityContextHolder.getContext().getAuthentication();
        CurrentUserInfo currentUserInfo = new CurrentUserInfo();
        if (authorization != null) {
            var principal = authorization.getPrincipal();
            if (principal instanceof AuthUser authUser)
                currentUserInfo.fromAuthUser(authUser);
        }
        return currentUserInfo;
    }

}
