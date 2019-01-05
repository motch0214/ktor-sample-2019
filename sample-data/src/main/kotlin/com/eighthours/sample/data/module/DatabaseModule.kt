package com.eighthours.sample.data.module

import com.eighthours.sample.data.DataManagerImpl
import com.eighthours.sample.data.DatabaseConfig
import com.eighthours.sample.data.TransactionManagerImpl
import com.eighthours.sample.service.DataManager
import com.eighthours.sample.service.TransactionManager
import com.typesafe.config.ConfigFactory
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.seasar.doma.jdbc.Config as DomaConfig

val DatabaseModule = Kodein.Module("DatabaseModule") {
    val root = ConfigFactory.load("doma")
    run {
        val config = DatabaseConfig("default", root.getConfig("default"))
        bind<DomaConfig>() with instance(config)
        bind<TransactionManager>() with provider { TransactionManagerImpl(config.transactionManager) }
    }

    bind<DataManager>() with provider { DataManagerImpl(kodein) }
}
