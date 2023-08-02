package gestion.incident.incident.auth;

import gestion.incident.incident.config.JwtService;
import gestion.incident.incident.enumeration.MesRoles;
import gestion.incident.incident.token.Token;
import gestion.incident.incident.token.TokenRepository;
import gestion.incident.incident.token.TokenType;
import gestion.incident.incident.utilisateur.UtilisateurRepository;
import lombok.RequiredArgsConstructor;

import gestion.incident.incident.utilisateur.Utilisateur;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.*;

import java.util.Optional;

@Builder
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UtilisateurRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {
        var user = Utilisateur.builder()
                .nom(request.getNom())
                .prenom(request.getPrenom())
                .email(request.getEmail())
                .mot_de_passe(passwordEncoder.encode(request.getMot_de_passe()))
                .role(request.getRole())
                .build();
        var savedUser = repository.save(user);
        var jwtToken=jwtService.generateToken(user);
        saveUsertoken(savedUser, jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }



    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getMot_de_passe()
                )

        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken=jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUsertoken(user, jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    private void revokeAllUserTokens(Utilisateur utilisateur){
        var validUserTokens = tokenRepository.findAllValidTokensByUser(Math.toIntExact(utilisateur.getId()));
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(t -> {
            t.setExpired(true);
            t.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    private void saveUsertoken(Utilisateur utilisateur, String jwtToken) {
        var token = Token.builder()
                .utilisateur(utilisateur)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .revoked(false)
                .expired(false)
                .build();
        tokenRepository.save(token);
    }

    public Utilisateur authUser(Authentication authentication) {
        String email = authentication.getName();
        Optional<Utilisateur> user = repository.findByEmail(email);
        System.out.println(user.isPresent());
        System.out.println(email);
        //user.orElseThrow(() -> new UsernameNotFoundException("Utilisateur Non trouvée!"));
        return user.get();
    }
}
