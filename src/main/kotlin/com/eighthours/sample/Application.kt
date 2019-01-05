package com.eighthours.sample

import com.eighthours.sample.config.dependencyConfig
import com.eighthours.sample.config.gsonConfiguration
import com.eighthours.sample.config.routingConfig
import com.eighthours.sample.web.BadRequestException
import com.eighthours.sample.web.MessageResponse
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.application.log
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.StatusPages
import io.ktor.gson.gson
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.util.error
import org.slf4j.event.Level

fun Application.module() {
    val kodein = dependencyConfig()

    install(CallLogging) {
        level = Level.DEBUG
    }

    install(Routing, routingConfig(kodein))

    install(ContentNegotiation) {
        gson(block = gsonConfiguration())
    }

    install(StatusPages) {
        exception<BadRequestException> { e ->
            call.respond(HttpStatusCode.fromValue(e.status), MessageResponse(e.message))
            log.debug(e.message, e)
        }
        exception<Throwable> { e ->
            call.respond(
                HttpStatusCode.InternalServerError, MessageResponse(e.message ?: "Internal Server Error")
            )
            log.error(e)
        }
    }
}
