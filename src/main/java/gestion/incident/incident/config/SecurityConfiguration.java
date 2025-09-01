package gestion.incident.incident.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static gestion.incident.incident.enumeration.MesRoles.ADMIN;
import static gestion.incident.incident.enumeration.MesRoles.DIRECTEUR;
import static gestion.incident.incident.enumeration.Permission.*;
import static jakarta.servlet.DispatcherType.ERROR;
import static jakarta.servlet.DispatcherType.FORWARD;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .csrf()
                .disable()
                .cors()
                .and()
                .authorizeHttpRequests(authorize -> authorize
                        .dispatcherTypeMatchers(FORWARD, ERROR).permitAll()
                        .requestMatchers("api/v1/auth/**", "api/files/**").permitAll()
                        //.requestMatchers("/api/**").hasAuthority("TECHNICIEN")
                        //.requestMatchers("/api/utilisateurs/**").hasAnyRole(ADMIN.name())
                        //Gestion des utilisateurs par l'admin
                        .requestMatchers(GET, "/api/utilisateurs/**").hasAnyAuthority(ADMIN_READ.name(), DIRECTEUR_READ.name(), TECHNICIEN_READ.name(), RESPONSABLE_READ.name())
                        .requestMatchers(POST, "/api/utilisateurs/**").hasAnyAuthority(ADMIN_CREATE.name(),TECHNICIEN_CREATE.name())
                        .requestMatchers(PUT, "/api/utilisateurs/**").hasAnyAuthority(ADMIN_UPDATE.name(), TECHNICIEN_UPDATE.name())
                        .requestMatchers(DELETE, "/api/utilisateurs/**").hasAnyAuthority(ADMIN_DELETE.name(), TECHNICIEN_DELETE.name())

                        //Gestion des agences par l'admin
                        .requestMatchers(GET, "/api/agences/**").hasAnyAuthority(ADMIN_READ.name(), DIRECTEUR_READ.name(), TECHNICIEN_READ.name(), RESPONSABLE_READ.name())
                        .requestMatchers(POST, "/api/agences/**").hasAnyAuthority(ADMIN_CREATE.name(), RESPONSABLE_CREATE.name(), TECHNICIEN_CREATE.name())
                        .requestMatchers(PUT, "/api/agences/**").hasAnyAuthority(ADMIN_UPDATE.name(), RESPONSABLE_UPDATE.name(), TECHNICIEN_UPDATE.name())
                        .requestMatchers(DELETE, "/api/agences/**").hasAnyAuthority(ADMIN_DELETE.name(), RESPONSABLE_DELETE.name(), TECHNICIEN_DELETE.name())
                        //Gestion des clients par l'admin
                        .requestMatchers(GET, "/api/clients/**").hasAnyAuthority(ADMIN_READ.name(), DIRECTEUR_READ.name(), TECHNICIEN_READ.name(), RESPONSABLE_READ.name())
                        .requestMatchers(POST, "/api/clients/**").hasAnyAuthority(ADMIN_CREATE.name(), RESPONSABLE_CREATE.name(), TECHNICIEN_CREATE.name())
                        .requestMatchers(PUT, "/api/clients/**").hasAnyAuthority(ADMIN_UPDATE.name(), RESPONSABLE_UPDATE.name(), TECHNICIEN_UPDATE.name())
                        .requestMatchers(DELETE, "/api/clients/**").hasAnyAuthority(ADMIN_DELETE.name(), RESPONSABLE_DELETE.name(), TECHNICIEN_DELETE.name())
                        //Gestion des incidents par l'admin,le technicien, le directeur, le responsable
                        .requestMatchers(GET, "/api/incidents/**").hasAnyAuthority(ADMIN_READ.name(), TECHNICIEN_READ.name(), DIRECTEUR_READ.name(), RESPONSABLE_READ.name())
                        .requestMatchers(POST, "/api/incidents/**").hasAnyAuthority(ADMIN_CREATE.name(), RESPONSABLE_CREATE.name(), TECHNICIEN_CREATE.name())
                        .requestMatchers(PUT, "/api/incidents/**").hasAnyAuthority(ADMIN_UPDATE.name(), RESPONSABLE_UPDATE.name(),TECHNICIEN_UPDATE.name())
                        .requestMatchers(DELETE, "/api/incidents/**").hasAnyAuthority(ADMIN_DELETE.name(), RESPONSABLE_DELETE.name(),TECHNICIEN_DELETE.name())
                        //Gestion des materiels par l'admin, le technicien, le directeur, le responsable
                        .requestMatchers(GET, "/api/materiels/**").hasAnyAuthority(ADMIN_READ.name(), TECHNICIEN_READ.name(), DIRECTEUR_READ.name(), RESPONSABLE_READ.name())
                        .requestMatchers(POST, "/api/materiels/**").hasAnyAuthority(ADMIN_CREATE.name(), RESPONSABLE_CREATE.name(), TECHNICIEN_CREATE.name())
                        .requestMatchers(PUT, "/api/materiels/**").hasAnyAuthority(ADMIN_UPDATE.name(), RESPONSABLE_UPDATE.name(), TECHNICIEN_UPDATE.name())
                        .requestMatchers(DELETE, "/api/materiels/**").hasAnyAuthority(ADMIN_DELETE.name(), RESPONSABLE_DELETE.name(), TECHNICIEN_DELETE.name())
                        //Gestion du mouvement des materiels par l'admin
                        .requestMatchers(GET, "/api/mouvements/**").hasAnyAuthority(ADMIN_READ.name(), DIRECTEUR_READ.name(), TECHNICIEN_READ.name(), RESPONSABLE_READ.name())
                        .requestMatchers(POST, "/api/mouvements/**").hasAnyAuthority(ADMIN_CREATE.name(), RESPONSABLE_CREATE.name(), TECHNICIEN_CREATE.name())
                        .requestMatchers(PUT, "/api/mouvements/**").hasAnyAuthority(ADMIN_UPDATE.name(), RESPONSABLE_UPDATE.name(), TECHNICIEN_UPDATE.name())
                        .requestMatchers(DELETE, "/api/mouvements/**").hasAnyAuthority(ADMIN_DELETE.name(), RESPONSABLE_DELETE.name(), TECHNICIEN_DELETE.name())
                        //Gestion des procedures par l'admin, le technicien, le directeur, le responsable
                        .requestMatchers(GET, "/api/procedures/**").hasAnyAuthority(ADMIN_READ.name(), TECHNICIEN_READ.name(), DIRECTEUR_READ.name(), RESPONSABLE_READ.name())
                        .requestMatchers(POST, "/api/procedures/**").hasAnyAuthority(ADMIN_CREATE.name(), TECHNICIEN_CREATE.name(), RESPONSABLE_CREATE.name())
                        .requestMatchers(PUT, "/api/procedures/**").hasAnyAuthority(ADMIN_UPDATE.name(), TECHNICIEN_UPDATE.name(), RESPONSABLE_UPDATE.name())
                        .requestMatchers(DELETE, "/api/procedures/**").hasAnyAuthority(ADMIN_DELETE.name(), TECHNICIEN_DELETE.name(), RESPONSABLE_DELETE.name())
                        .anyRequest().authenticated()
                )
                /*.requestMatchers("/api/v1/auth/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()*/
                .cors(withDefaults())
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .logout()
                .logoutUrl("/api/v1/auth/logout")
                .addLogoutHandler(logoutHandler)
                .logoutSuccessHandler(((request, response, authentication) -> SecurityContextHolder.clearContext()));

        return http.build();
    }


}
