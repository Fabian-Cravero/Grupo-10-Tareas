package utn.methodology.domain.entities

import java.util.UUID

class Usuario (
    val uuid: String,
    var name: String,
    var username: String,
    var email: String,
    var password: String,
){
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

fun getIdUser(): String {
    return this.uuid;
}

fun update(name: String, username: String, email: String, password: String) {
    this.name = name;
    this.username = username;
    this.email = email;
    this.password = password;
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



