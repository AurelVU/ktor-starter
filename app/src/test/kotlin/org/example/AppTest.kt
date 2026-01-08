package org.example

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class AppTest {
    @Test
    fun userRepositoryReturnsUsers() {
        val users = UserRepository.getAll()
        assertTrue(users.size >= 2, "Should have at least 2 initial users")
    }

    @Test
    fun userRepositoryFindsUserById() {
        val user = UserRepository.getById(1)
        assertNotNull(user)
        assertEquals("John Doe", user.name)
    }

    @Test
    fun userRepositoryCreatesUser() {
        val initialCount = UserRepository.getAll().size
        val newUser = UserRepository.create(CreateUserRequest("Test User", "test@test.com", 25))
        assertNotNull(newUser.id)
        assertEquals("Test User", newUser.name)
        assertEquals(initialCount + 1, UserRepository.getAll().size)
    }
}
