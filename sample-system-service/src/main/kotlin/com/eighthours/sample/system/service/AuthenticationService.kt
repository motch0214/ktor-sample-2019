package com.eighthours.sample.system.service

import com.eighthours.sample.domain.StringId
import com.eighthours.sample.system.domain.User

interface AuthenticationService {

    fun authenticate(userId: StringId<User>, password: String): UserSession?
}
