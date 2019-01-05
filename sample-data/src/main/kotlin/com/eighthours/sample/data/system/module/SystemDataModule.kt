package com.eighthours.sample.data.system.module

import com.eighthours.sample.data.createDao
import com.eighthours.sample.data.system.UserDao
import com.eighthours.sample.data.system.UserRepositoryImpl
import com.eighthours.sample.system.domain.UserRepository
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider

val SystemDataModule = Kodein.Module("SystemDataModule") {
    // DAOs
    bind<UserDao>() with provider { createDao<UserDao>() }

    // Repositories
    bind<UserRepository>() with provider { UserRepositoryImpl(kodein) }
}
