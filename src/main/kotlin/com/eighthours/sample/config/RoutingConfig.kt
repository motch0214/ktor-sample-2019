package com.eighthours.sample.config

import com.eighthours.sample.web.LoginController
import com.eighthours.sample.web.UserController
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.routing.Route
import io.ktor.sessions.sessions
import io.ktor.sessions.set
import org.kodein.di.Kodein

internal fun Route.routingConfig(kodein: Kodein) {
    with(LoginController(kodein)) {
        route("/login") {
            val (response, session) = login(request())
            response.also { call.sessions.set(session) }
        }
    }

    authenticate {
        with(UserController(kodein)) {
            route("/user/find") { find(request()) }
            route("/user/create") { create(request()) }
            route("/user/delete") { delete(request()) }
        }
    }
}
