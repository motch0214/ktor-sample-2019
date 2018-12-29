package com.eighthours.sample

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.http.ContentType
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get
import org.slf4j.event.Level


fun Application.module() {
    install(CallLogging) {
        level = Level.DEBUG
    }
    install(Routing) {
        get("/") {
            call.respondText("Hello world.", ContentType.Text.Html)
        }
    }
}
