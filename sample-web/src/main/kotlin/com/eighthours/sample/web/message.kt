package com.eighthours.sample.web

data class MessageResponse(val message: String)

class BusinessException(message: String, cause: Throwable? = null) : RuntimeException(message, cause)
