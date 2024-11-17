package tigo.aplanchados.config;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;
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
                .cors(corsCustomizer->corsCustomizer.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration corsConfiguration=new CorsConfiguration();
                        corsConfiguration.setAllowCredentials(false);// allows taking authentication with credentials
                        corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
                      // providing the allowed origin details, can provide multiple origins here, 7070 is the port number of client application here
                        corsConfiguration.setAllowedMethods(Collections.singletonList("*"));// allowing all HTTP methods GET,POST,PUT etc, can configure on your need
                        corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));// allowing all the request headers, can configure according to your need, which headers to allow
                        corsConfiguration.setMaxAge(Duration.ofMinutes(5L)); // setting the max time till which the allowed origin will not make a pre-flight request again to check if the CORS is allowed on not
                        return corsConfiguration;
                    }
                }))
                                .csrf(AbstractHttpConfigurer::disable)
                                .authorizeHttpRequests(authz -> authz
                                                .requestMatchers(HttpMethod.POST,"/login", "/user/create", "/css/**", "/js/**", "/","/auth/register","/auth/authenticate",
                                                                "images/**")
                                                .permitAll()
                                                .requestMatchers(HttpMethod.GET,"/auth/test").permitAll()
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
