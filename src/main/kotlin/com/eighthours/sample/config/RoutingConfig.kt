package com.eighthours.sample.config

import com.eighthours.sample.web.UserController
import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get
import org.kodein.di.Kodein

internal fun routingConfig(kodein: Kodein): Routing.() -> Unit = {

    get("/") {
        call.respondText("Hello world.", ContentType.Text.Html)
    }

    with(UserController(kodein)) {
        route("/user/find") { find(request()) }
        route("/user/create") { create(request()) }
    }
}
