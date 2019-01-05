package com.eighthours.sample.config

import com.eighthours.sample.web.UserController
import io.ktor.routing.Routing
import org.kodein.di.Kodein

internal fun routingConfig(kodein: Kodein): Routing.() -> Unit = {

    with(UserController(kodein)) {
        route("/user/find") { find(request()) }
        route("/user/create") { create(request()) }
        route("/user/delete") { delete(request()) }
    }
}
