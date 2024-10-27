package tigo.aplanchados.config;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor

public class SecurityConfiguration {
        private final JwtAuthenticationFilter jwtAuthFilter;
        private final AuthenticationProvider authenticationProvider;

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .csrf(AbstractHttpConfigurer::disable)
                                .authorizeHttpRequests(authz -> authz
                                                .requestMatchers(HttpMethod.POST,"/login", "/user/create", "/css/**", "/js/**", "/","/auth/register","/auth/authenticate",
                                                                "images/**")
                                                .permitAll()
                                                .requestMatchers(HttpMethod.POST,"/expense/create", "/income/create", "/dashboard",
                                                                "/expense", "/income")
                                                .hasAnyAuthority("ADD EXPENSE", "ADD INCOME")
                                                .requestMatchers("/manager/**", "/manager/roles", "/manager/add-role",
                                                                "/manager/remove-role", "/manager/update-roles",
                                                                "/manager/edit-user-role")
                                                .hasAnyAuthority("MANAGE SYSTEM")
                                                .requestMatchers(HttpMethod.POST, "/excel", "/upload-excel", "/generate-excel")
                                                .hasAnyAuthority("GENERATE REPORT")
                                                .anyRequest().authenticated())
                                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                                .authenticationProvider(authenticationProvider)
                                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)

                ;

                return http.build();

        }
}
