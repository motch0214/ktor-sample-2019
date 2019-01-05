package com.eighthours.sample

import com.eighthours.sample.config.dependencyConfig
import com.eighthours.sample.service.DataManager
import io.ktor.application.Application
import io.ktor.http.HttpMethod
import io.ktor.server.testing.*
import org.junit.jupiter.api.BeforeEach
import org.kodein.di.direct
import org.kodein.di.generic.instance

abstract class WebTestBase {

    @BeforeEach
    fun setup() {
        val kodein = dependencyConfig()
        kodein.direct.instance<DataManager>().initialize()
    }

    protected fun application(
        module: Application.() -> Unit = Application::module,
        test: TestApplicationEngine.() -> Unit
    ) {
        withTestApplication(module, test)
    }

    protected fun TestApplicationEngine.url(url: String): Pair<TestApplicationEngine, String> {
        return this to url
    }

    protected fun Pair<TestApplicationEngine, String>.post(body: String, block: TestApplicationCall.() -> Unit) {
        block(first.handleRequest(HttpMethod.Post, second) {
            setBody(body)
        })
    }
}
