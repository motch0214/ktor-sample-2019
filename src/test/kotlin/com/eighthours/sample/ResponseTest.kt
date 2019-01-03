package com.eighthours.sample

import assertk.assert
import assertk.assertions.isEqualTo
import io.ktor.application.Application
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.withTestApplication
import org.junit.jupiter.api.Test

class ResponseTest {

    @Test
    fun test_HelloWorld() = withTestApplication(Application::module) {
        with(handleRequest(HttpMethod.Get, "/")) {
            assert(response.status()).isEqualTo(HttpStatusCode.OK)
            assert(response.content).isEqualTo("Hello world.")
        }
    }
}
