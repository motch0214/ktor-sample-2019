package com.eighthours.sample.data

import com.eighthours.sample.service.TransactionManager

class TransactionManagerImpl(
    private val tx: org.seasar.doma.jdbc.tx.TransactionManager
) : TransactionManager {

    override fun <T> apply(block: () -> T): T {
        return tx.required(block)
    }
}
