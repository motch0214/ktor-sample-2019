package com.eighthours.sample

import com.eighthours.sample.config.authConfig
import com.eighthours.sample.config.dependencyConfig
import com.eighthours.sample.config.gsonConfig
import com.eighthours.sample.config.routingConfig
import com.eighthours.sample.system.service.UserSession
import com.eighthours.sample.web.BadRequestException
import com.eighthours.sample.web.MessageResponse
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.application.log
import io.ktor.auth.Authentication
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.StatusPages
import io.ktor.gson.gson
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.sessions.SessionStorageMemory
import io.ktor.sessions.Sessions
import io.ktor.sessions.header
import io.ktor.util.error
import org.slf4j.event.Level

fun Application.module() {
    val kodein = dependencyConfig()

    install(CallLogging) {
        level = Level.DEBUG
    }

    install(Authentication) {
        authConfig(kodein)
    }

    install(Routing) {
        routingConfig(kodein)
    }

    install(Sessions) {
        header<UserSession>("SAMPLE_SESSION", SessionStorageMemory())
    }

    install(ContentNegotiation) {
        gson { gsonConfig() }
    }

    install(StatusPages) {
        exception<BadRequestException> { e ->
            call.respond(HttpStatusCode.fromValue(e.status), MessageResponse(e.message))
        }
        exception<Throwable> { e ->
            call.respond(HttpStatusCode.InternalServerError, MessageResponse(e.message ?: "Internal Server Error"))
            log.error(e)
        }
    }
}
