package utn.methodology

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.testing.*
import io.ktor.server.response.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Test
import kotlin.test.assertEquals

class UserCreationPostTest {

    // creacion exitosa
    @Test
    fun create_user_should_returns_201() {
        // SOLUCIONAR ESTO QUE ESTÁ DEPRECADO
        withTestApplication(Application::module) {
            // datos ficticios
            val postData = PostData(
                uuid = "post-uuid",
                uuidUser = "user-uuid",
                userId = "userId",
                text = "This is a test post",
                date = "2024-11-03",
                createdAt = "2024-11-03T10:00:00"
            )

            // llamada al endpoint 'POST', crea publicacion y verifica respuesta
            handleRequest(HttpMethod.Post, "/posts") {
                addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
                setBody(Json.encodeToString(postData))
            }.apply {
                assertEquals(HttpStatusCode.Created, response.status())
                assertEquals("User post created successfully", response.content)
            }
        }
    }

    // creacion no exitosa
    @Test
    fun create_user_should_returns_400() {
        // SOLUCIONAR ESTO QUE ESTÁ DEPRECADO
        withTestApplication(Application::module) {
            // llamada al endpoint 'POST', sin los campos y verifica respuesta
            handleRequest(HttpMethod.Post, "/posts") {
                addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
                setBody(Json.encodeToString(mapOf("text" to "This post has no user data")))
            }.apply {
                assertEquals(HttpStatusCode.BadRequest, response.status())
                assertEquals("User ID is missing", response.content)
            }
        }
    }

    @Serializable
    data class PostData(
        val uuid: String,
        val uuidUser: String,
        val userId: String,
        val text: String,
        val date: String,
        val createdAt: String
    )
}
