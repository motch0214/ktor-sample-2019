package com.eighthours.sample.system.service

interface SessionValidationService {

    fun isValid(session: UserSession): Boolean
}
