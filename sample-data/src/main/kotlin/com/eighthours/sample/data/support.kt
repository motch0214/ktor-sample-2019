package com.eighthours.sample.data

import org.kodein.di.DKodeinAware
import org.kodein.di.generic.instance

internal inline fun <reified T> DKodeinAware.createDao(): T {
    val config: org.seasar.doma.jdbc.Config = instance()
    return Class.forName(T::class.java.name + "Impl")
        .getDeclaredConstructor(org.seasar.doma.jdbc.Config::class.java)
        .newInstance(config) as T
}
