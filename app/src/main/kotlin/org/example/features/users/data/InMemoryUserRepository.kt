package org.example.features.users.data

import org.example.features.users.domain.model.User
import org.example.features.users.domain.repository.UserRepository
import org.example.features.users.presentation.dto.CreateUserRequest
import org.example.features.users.presentation.dto.UpdateUserRequest
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong

class InMemoryUserRepository : UserRepository {
    private val users = ConcurrentHashMap<Long, User>()
    private val idCounter = AtomicLong(1)

    init {
        // Add sample data
        create(CreateUserRequest("John Doe", "john@example.com", 30))
        create(CreateUserRequest("Jane Smith", "jane@example.com", 25))
    }

    override fun getAll(): List<User> = users.values.toList()

    override fun getById(id: Long): User? = users[id]

    override fun create(request: CreateUserRequest): User {
        val id = idCounter.getAndIncrement()
        val user = User(id, request.name, request.email, request.age)
        users[id] = user
        return user
    }

    override fun update(id: Long, request: UpdateUserRequest): User? {
        val existing = users[id] ?: return null
        val updated = existing.copy(
            name = request.name ?: existing.name,
            email = request.email ?: existing.email,
            age = request.age ?: existing.age
        )
        users[id] = updated
        return updated
    }

    override fun delete(id: Long): Boolean = users.remove(id) != null
}
