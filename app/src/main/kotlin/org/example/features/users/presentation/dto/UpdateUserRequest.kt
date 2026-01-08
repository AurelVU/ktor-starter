package org.example.features.users.presentation.dto

import kotlinx.serialization.Serializable

@Serializable
data class UpdateUserRequest(
    val name: String? = null,
    val email: String? = null,
    val age: Int? = null
)
