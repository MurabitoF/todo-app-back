package com.example.todo.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.Date


@Component
class JwtUtils {
    private val JWT_KEY: String = "K31.p4R4.Jw7"
    private val EXPIRATION_DATE = 36000000

    fun generateToken(userDetails: UserDetails): String {
        return Jwts.builder()
            .setSubject(userDetails.username)
            .setIssuedAt(Date())
            .setExpiration(Date(Date().time + EXPIRATION_DATE))
            .signWith(SignatureAlgorithm.HS256, JWT_KEY)
            .compact()
    }

    fun validateToken(token: String, userDetails: UserDetails): Boolean {
        return userDetails.username.equals(extractUsername(token)) && !isTokenExpired(token)
    }

    fun extractUsername(token: String): String = getClaims(token).subject

    fun isTokenExpired(token: String): Boolean = getClaims(token).expiration.before(Date())

    fun getClaims(token: String): Claims = Jwts.parser().setSigningKey(JWT_KEY).parseClaimsJws(token).body
}