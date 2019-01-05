package com.eighthours.sample.config

import com.eighthours.sample.data.module.DatabaseModule
import com.eighthours.sample.data.system.module.SystemDataModule
import org.kodein.di.Kodein
import org.slf4j.bridge.SLF4JBridgeHandler

internal fun dependencyConfig() = Kodein {
    // java.util.logging -> slf4j
    SLF4JBridgeHandler.removeHandlersForRootLogger()
    SLF4JBridgeHandler.install()

    import(SystemDataModule)
    import(DatabaseModule)
}
