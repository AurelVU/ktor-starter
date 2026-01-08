package org.example.features.users.domain.repository

import org.example.features.users.domain.model.User
import org.example.features.users.presentation.dto.CreateUserRequest
import org.example.features.users.presentation.dto.UpdateUserRequest

interface UserRepository {
    fun getAll(): List<User>
    fun getById(id: Long): User?
    fun create(request: CreateUserRequest): User
    fun update(id: Long, request: UpdateUserRequest): User?
    fun delete(id: Long): Boolean
}
