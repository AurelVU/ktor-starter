package org.example.plugins

import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.example.features.health.presentation.healthRoutes
import org.example.features.users.domain.repository.UserRepository
import org.example.features.users.presentation.userRoutes
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val userRepository by inject<UserRepository>()

    routing {
        userRoutes(userRepository)
        healthRoutes()
    }
}
