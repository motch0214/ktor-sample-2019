package com.eighthours.sample.service

interface TransactionManager {

    fun <T> apply(block: () -> T): T
}
