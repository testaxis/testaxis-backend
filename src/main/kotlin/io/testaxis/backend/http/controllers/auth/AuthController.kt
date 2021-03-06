package io.testaxis.backend.http.controllers.auth

// Based on https://www.callicoder.com/spring-boot-security-oauth2-social-login-part-1

import io.testaxis.backend.exceptions.CustomValidationException
import io.testaxis.backend.models.AuthProvider
import io.testaxis.backend.models.User
import io.testaxis.backend.repositories.UserRepository
import io.testaxis.backend.security.TokenProvider
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView
import javax.validation.Valid
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

@RestController
@Validated
class AuthController(
    val authenticationManager: AuthenticationManager,
    val userRepository: UserRepository,
    val passwordEncoder: PasswordEncoder,
    val tokenProvider: TokenProvider
) {
    @PostMapping("/api/v1/auth/login")
    fun authenticateUser(@RequestBody @Valid loginRequest: LoginRequest) =
        AuthResponse(login(loginRequest.email, loginRequest.password))

    @PostMapping("/api/v1/auth/register")
    fun registerUser(@RequestBody @Valid signUpRequest: SignUpRequest): AuthResponse {
        if (userRepository.existsByEmail(signUpRequest.email)) {
            throw CustomValidationException("email", "Email address already in use.")
        }

        userRepository.save(
            User(
                name = signUpRequest.name,
                email = signUpRequest.email,
                password = passwordEncoder.encode(signUpRequest.password),
                provider = AuthProvider.Local,
            )
        )

        return AuthResponse(login(signUpRequest.email, signUpRequest.password))
    }

    @GetMapping("/auth/token")
    fun displayToken(@RequestParam token: String) = ModelAndView("token", mapOf("token" to token))

    private fun login(email: String, password: String): String {
        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(email, password)
        )

        SecurityContextHolder.getContext().authentication = authentication

        return tokenProvider.createToken(authentication)
    }
}

data class LoginRequest(
    @field:NotBlank @field:Email val email: String,
    @field:NotBlank val password: String
)

data class SignUpRequest(
    @field:NotBlank val name: String,
    @field:NotBlank @field:Email val email: String,
    @field:NotBlank val password: String
)

data class AuthResponse(val accessToken: String, val tokenType: String = "Bearer")
