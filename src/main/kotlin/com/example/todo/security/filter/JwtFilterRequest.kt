package com.example.todo.security.filter

import com.example.todo.security.JwtUtils
import com.example.todo.service.UserService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtFilterRequest(@Autowired val jwtUtils: JwtUtils,
                       @Autowired val userService: UserService): OncePerRequestFilter()
{
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authorizationHeader = request.getHeader("Authorization")

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")){
            val jwt = authorizationHeader.removePrefix("Bearer ")
            val username = jwtUtils.extractUsername(jwt)


            if(username != null && SecurityContextHolder.getContext().authentication == null) {
                val userDetails = userService.loadUserByUsername(username)

                if (jwtUtils.validateToken(jwt, userDetails)){
                    val authToken = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
                    authToken.details = WebAuthenticationDetailsSource().buildDetails(request)

                    SecurityContextHolder.getContext().authentication = authToken
                }
            }
        }

        filterChain.doFilter(request, response)
    }
}