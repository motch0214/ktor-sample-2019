package com.eighthours.sample.config

import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.post
import io.ktor.util.pipeline.PipelineContext

internal inline fun Route.route(
    path: String,
    crossinline block: suspend PipelineContext<Unit, ApplicationCall>.() -> Any
) {
    post(path) {
        call.respond(block())
    }
}

internal suspend inline fun <reified T : Any> PipelineContext<Unit, ApplicationCall>.request() = call.receive(T::class)
