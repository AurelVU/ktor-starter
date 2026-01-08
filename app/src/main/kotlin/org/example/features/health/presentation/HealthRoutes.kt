package org.example.features.health.presentation

import io.github.smiley4.ktoropenapi.get
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.example.features.health.presentation.dto.HealthResponse

fun Route.healthRoutes() {
    get("/health", {
        tags = listOf("System")
        summary = "Health check"
        description = "Returns the health status of the API"
        response {
            HttpStatusCode.OK to {
                description = "API is healthy"
                body<HealthResponse>()
            }
        }
    }) {
        call.respond(HealthResponse("OK", System.currentTimeMillis()))
    }
}
