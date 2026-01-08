package org.example.plugins

import io.github.smiley4.ktoropenapi.OpenApi
import io.github.smiley4.ktoropenapi.openApi
import io.github.smiley4.ktorswaggerui.swaggerUI
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureOpenAPI() {
    install(OpenApi) {
        info {
            title = "User Management API"
            version = "1.0.0"
            description = "Simple REST API example with Ktor and auto-generated Swagger documentation"
            contact {
                name = "API Support"
                email = "support@example.com"
            }
        }
        server {
            url = "http://localhost:8080"
            description = "Development server"
        }
    }

    routing {
        route("/swagger") {
            swaggerUI("/api.json")
        }
        route("/api.json") {
            openApi()
        }
    }
}
