package com.eighthours.sample.web

data class MessageResponse(val message: String)

class BusinessException(message: String) : RuntimeException(message)
