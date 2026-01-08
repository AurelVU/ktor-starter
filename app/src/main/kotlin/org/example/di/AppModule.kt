package org.example.di

import org.example.features.users.data.InMemoryUserRepository
import org.example.features.users.domain.repository.UserRepository
import org.koin.dsl.module

val appModule = module {
    single<UserRepository> { InMemoryUserRepository() }
}
