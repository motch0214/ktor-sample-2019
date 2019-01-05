package com.eighthours.sample.data

import com.eighthours.sample.data.system.UserDao
import com.eighthours.sample.service.DataManager
import com.eighthours.sample.service.TransactionManager
import org.kodein.di.Kodein
import org.kodein.di.direct
import org.kodein.di.generic.instance

class DataManagerImpl(private val kodein: Kodein) : DataManager {

    private val transaction: TransactionManager by kodein.instance()

    override fun initialize() = transaction.apply {
        kodein.direct.instance<UserDao>().create()
    }
}
