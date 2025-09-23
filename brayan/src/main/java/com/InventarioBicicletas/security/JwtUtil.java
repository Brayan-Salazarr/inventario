package com.InventarioBicicletas.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

       private static final String SECRET = "clave_super_secreta_que_tiene_que_ser_larga_123456789";
    private static final long EXPIRATION = 10000000000L; // 10000 segundos
    private static final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    public static String generarToken(String usuario) {
        return Jwts.builder()
                .setSubject(usuario)
                .setIssuedAt(new Date()) // This now works
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // Método para validar el token en todos los endpoints contreollelr
public static boolean validarToken(String token) {
    try {
        Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token);
        return true; 
    } catch (Exception e) {
        return false; 
    }
}
}
