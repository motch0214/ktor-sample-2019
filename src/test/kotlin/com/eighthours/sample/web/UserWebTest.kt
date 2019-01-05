package com.eighthours.sample.web

import com.eighthours.sample.WebTestBase
import com.google.gson.JsonParser
import io.ktor.http.HttpStatusCode
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class UserWebTest : WebTestBase() {

    @Test
    fun test_create() = application {
        url("/user/create").post(
            """
            {
                "id": "test-user",
                "name": "Test User",
                "roles": [
                    "A", "B"
                ]
            }
            """
        ) {
            assertThat(response.status()).isEqualTo(HttpStatusCode.OK)
        }

        url("/user/find").post(
            """
            {
                "id": "test-user"
            }
            """
        ) {
            assertThat(response.status()).isEqualTo(HttpStatusCode.OK)
            val content = JsonParser().parse(response.content).asJsonObject
            assertThat(content["id"].asString).isEqualTo("test-user")
            assertThat(content["name"].asString).isEqualTo("Test User")
            val roles = content["roles"].asJsonArray.map { it.asString }
            assertThat(roles).containsExactlyInAnyOrder("A", "B")
        }
    }
}
