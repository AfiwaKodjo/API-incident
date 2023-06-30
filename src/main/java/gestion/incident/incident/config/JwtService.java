package gestion.incident.incident.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String SECRET_KEY="MCsI51z7r1lZBotikVFJe5nIm44PUSiC5/Ch+b2GWBl8NLrQ2iJmodzBsLMmlGBjV8xuSnhGTqpd3U2Vnjowu2hiE/X4UDM/FuQf9j2aAhUhR8Lm5ilzkeJfw0J45zGXLt+c5eIu9VYPmLbbeQ3p+/qtYZy9WoF9n6n713aIlfrq6brH09FCX1mIl7j3AJthScFpAf0axzkiyg6yGMW2ilWTCYsf5cbeYMh1RLm89waXrwMyuae7GvXE7RJEsxZYvLW3uAscDtIEGSBx7uAhnk2s2Y+Z+m9MzFbppTUi2YhkbFE1nNGFZ/Q1+NWh0AcVjRbFxWOLeQfeg6G/vD+y90iwfr0d1IGWycfOEn/+9SE=";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims=extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes= Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ){
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 15*60*1000))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }



    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username=extractUsername(token);
        return (username.equals(userDetails.getUsername()))&& !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
}




}
