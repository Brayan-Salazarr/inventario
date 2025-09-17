package com.InventarioBicicletas.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.util.Date;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {

    private static final String SECRET = "clave_super_secreta_que_tiene_que_ser_larga_123456789";
    private static final long EXPIRATION = 1000 * 60 * 60; // 1 hora
    private static final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    public static String generarToken(String usuario) {
        return Jwts.builder()
                .setSubject(usuario)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}
