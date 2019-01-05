package com.eighthours.sample.web

import com.eighthours.sample.WebTestBase
import com.eighthours.sample.system.domain.UserStatus
import com.google.gson.JsonParser
import io.ktor.http.HttpStatusCode
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class UserWebTest : WebTestBase() {

    @Test
    fun test() = application {
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
            assertThat(content["status"].asString).isEqualTo(UserStatus.ACTIVE.name)
            val roles = content["roles"].asJsonArray.map { it.asString }
            assertThat(roles).containsExactlyInAnyOrder("A", "B")
        }

        url("/user/delete").post(
            """
            {
                "id": "test-user",
                "version": 0
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
            assertThat(response.status()).isEqualTo(HttpStatusCode.NotFound)
        }
    }
}
