package wizard.fems.common.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import wizard.fems.common.security.enums.Role;

/**
 * packageName      : wizard.fems.common.config
 * fileName         : SecurityConfig
 * author           : sooJeong
 * date             : 2024-09-06
 * ======================================================
 * DATE             AUTHOR               NOTE
 * ------------------------------------------------------
 * 2024-09-06         sooJeong             최초 생성
 */

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final FemsAccessDeniedHandler accessDeniedHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf((csrf)
                        -> csrf.disable()
                 )
                .authorizeHttpRequests((authorizeRequest) -> authorizeRequest
//                                .requestMatchers("/login").permitAll()
//                                .requestMatchers("/error").permitAll()
//                                .requestMatchers("/img/**", "/common/**", "/lib/**").permitAll()
//                                .requestMatchers("/syetemMgmt/**").hasAuthority(Role.ADMIN.name())
                                .anyRequest().authenticated()
                                      )
                .formLogin((formLogin) -> formLogin
//                                .loginPage("/login")
                                .loginProcessingUrl("/login/login")
                                .usernameParameter("id")
                                .passwordParameter("password")
                                .defaultSuccessUrl("/")
                          )
                .exceptionHandling((exception) -> exception
                                .accessDeniedHandler(accessDeniedHandler)
                                  );

        http.logout((logOut) ->
                        logOut
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/")
                                .invalidateHttpSession(true)
                   );

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
