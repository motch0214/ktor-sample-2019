package com.eighthours.sample

import com.eighthours.sample.config.dependencyConfig
import com.eighthours.sample.config.gsonConfiguration
import com.eighthours.sample.config.routingConfig
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.routing.Routing
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
}
