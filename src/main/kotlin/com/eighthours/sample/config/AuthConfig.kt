package com.eighthours.sample.config

import com.eighthours.sample.system.service.SessionValidationService
import com.eighthours.sample.system.service.UserSession
import com.eighthours.sample.web.BadRequestException
import io.ktor.application.call
import io.ktor.auth.Authentication
import io.ktor.auth.AuthenticationPipeline
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import org.kodein.di.Kodein
import org.kodein.di.generic.instance

internal fun Authentication.Configuration.authConfig(kodein: Kodein) {
    val sessionValidator: SessionValidationService by kodein.instance()

    provider {
        pipeline.intercept(AuthenticationPipeline.CheckAuthentication) {
            val session = call.sessions.get<UserSession>()
                ?: throw BadRequestException("No Session", status = 401)
            val valid = sessionValidator.isValid(session)
            if (!valid) {
                throw BadRequestException("Invalid Session", status = 401)
            }
        }
    }
}
