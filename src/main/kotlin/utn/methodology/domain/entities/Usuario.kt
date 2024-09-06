package utn.methodology.domain.entities

import java.util.UUID

class Usuario (
    val uuid: String,
    var name: String,
    var username: String,
    var email: String,
    var password: String,
){
    fun getId(): String {
        return this.uuid;
    }
    companion object {
        fun fromPrimitives(primitives: Map<String, String>): Usuario {

            val user = Usuario(
                primitives["uuid"] as String,
                primitives["name"] as String,
                primitives["username"] as String,
                primitives["email"] as String,
                primitives["password"] as String,
            );
            return user;
        }
    }
    fun toPrimitives(): Map<String, String> {
        return mapOf(
            "uuid" to this.uuid,
            "name" to this.name,
            "username" to this.username,
            "email" to this.email,
            "password" to this.password,
        )
    }
}

