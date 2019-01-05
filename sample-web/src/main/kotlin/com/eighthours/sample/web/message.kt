package com.eighthours.sample.web

data class MessageResponse(val message: String)

class BadRequestException(
    override val message: String, cause: Throwable? = null, val status: Int = 400
) : RuntimeException(message, cause)
