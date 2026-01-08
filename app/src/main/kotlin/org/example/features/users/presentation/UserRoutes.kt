package org.example.features.users.presentation

import io.github.smiley4.ktoropenapi.get
import io.github.smiley4.ktoropenapi.post
import io.github.smiley4.ktoropenapi.put
import io.github.smiley4.ktoropenapi.delete
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.example.common.dto.ErrorResponse
import org.example.features.users.domain.model.User
import org.example.features.users.domain.repository.UserRepository
import org.example.features.users.presentation.dto.CreateUserRequest
import org.example.features.users.presentation.dto.UpdateUserRequest

fun Route.userRoutes(userRepository: UserRepository) {
    route("/api/users") {
        // GET all users
        get({
            tags = listOf("Users")
            summary = "Get all users"
            description = "Returns a list of all users in the system"
            response {
                HttpStatusCode.OK to {
                    description = "List of users"
                    body<List<User>> {
                        description = "Array of user objects"
                    }
                }
            }
        }) {
            call.respond(userRepository.getAll())
        }

        // GET user by ID
        get("/{id}", {
            tags = listOf("Users")
            summary = "Get user by ID"
            description = "Returns a single user by their ID"
            request {
                pathParameter<Long>("id") {
                    description = "User ID"
                    required = true
                    example("default") { value = 1L }
                }
            }
            response {
                HttpStatusCode.OK to {
                    description = "User found"
                    body<User> {
                        description = "User object"
                    }
                }
                HttpStatusCode.NotFound to {
                    description = "User not found"
                    body<ErrorResponse> {
                        description = "Error details"
                    }
                }
            }
        }) {
            val id = call.parameters["id"]?.toLongOrNull()
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, ErrorResponse("Invalid user ID", 400))
                return@get
            }

            val user = userRepository.getById(id)
            if (user != null) {
                call.respond(user)
            } else {
                call.respond(HttpStatusCode.NotFound, ErrorResponse("User not found", 404))
            }
        }

        // POST create user
        post({
            tags = listOf("Users")
            summary = "Create a new user"
            description = "Creates a new user and returns the created object"
            request {
                body<CreateUserRequest> {
                    description = "User data to create"
                    required = true
                    example("default") {
                        value = CreateUserRequest(
                            name = "New User",
                            email = "newuser@example.com",
                            age = 28
                        )
                    }
                }
            }
            response {
                HttpStatusCode.Created to {
                    description = "User created successfully"
                    body<User> {
                        description = "Created user object"
                    }
                }
                HttpStatusCode.BadRequest to {
                    description = "Invalid request data"
                    body<ErrorResponse> {
                        description = "Error details"
                    }
                }
            }
        }) {
            val request = call.receive<CreateUserRequest>()
            val user = userRepository.create(request)
            call.respond(HttpStatusCode.Created, user)
        }

        // PUT update user
        put("/{id}", {
            tags = listOf("Users")
            summary = "Update an existing user"
            description = "Updates user fields (partial update supported)"
            request {
                pathParameter<Long>("id") {
                    description = "User ID"
                    required = true
                }
                body<UpdateUserRequest> {
                    description = "Fields to update"
                    required = true
                    example("default") {
                        value = UpdateUserRequest(
                            name = "Updated Name",
                            email = "updated@example.com"
                        )
                    }
                }
            }
            response {
                HttpStatusCode.OK to {
                    description = "User updated successfully"
                    body<User> {
                        description = "Updated user object"
                    }
                }
                HttpStatusCode.NotFound to {
                    description = "User not found"
                    body<ErrorResponse> {
                        description = "Error details"
                    }
                }
            }
        }) {
            val id = call.parameters["id"]?.toLongOrNull()
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, ErrorResponse("Invalid user ID", 400))
                return@put
            }

            val request = call.receive<UpdateUserRequest>()
            val user = userRepository.update(id, request)
            if (user != null) {
                call.respond(user)
            } else {
                call.respond(HttpStatusCode.NotFound, ErrorResponse("User not found", 404))
            }
        }

        // DELETE user
        delete("/{id}", {
            tags = listOf("Users")
            summary = "Delete a user"
            description = "Permanently deletes a user by ID"
            request {
                pathParameter<Long>("id") {
                    description = "User ID to delete"
                    required = true
                }
            }
            response {
                HttpStatusCode.NoContent to {
                    description = "User deleted successfully"
                }
                HttpStatusCode.NotFound to {
                    description = "User not found"
                    body<ErrorResponse> {
                        description = "Error details"
                    }
                }
            }
        }) {
            val id = call.parameters["id"]?.toLongOrNull()
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, ErrorResponse("Invalid user ID", 400))
                return@delete
            }

            if (userRepository.delete(id)) {
                call.respond(HttpStatusCode.NoContent)
            } else {
                call.respond(HttpStatusCode.NotFound, ErrorResponse("User not found", 404))
            }
        }
    }
}
