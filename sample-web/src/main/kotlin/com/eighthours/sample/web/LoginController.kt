package com.eighthours.sample.web

import com.eighthours.sample.domain.StringId
import com.eighthours.sample.service.TransactionManager
import com.eighthours.sample.system.domain.User
import com.eighthours.sample.system.service.AuthenticationService
import com.eighthours.sample.system.service.UserSession
import org.kodein.di.Kodein
import org.kodein.di.generic.instance

class LoginController(kodein: Kodein) {

    private val authenticationService: AuthenticationService by kodein.instance()

    private val transaction: TransactionManager by kodein.instance()

    data class Login(val userId: StringId<User>, val password: String)

    data class LoginResponse(val username: String)

    fun login(request: Login): Pair<LoginResponse, UserSession> = transaction.apply {
        val session = authenticationService.authenticate(request.userId, request.password)
            ?: throw BadRequestException("Unauthorized", status = 401)
        LoginResponse(session.user.name) to session
    }
}
