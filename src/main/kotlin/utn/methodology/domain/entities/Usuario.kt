package utn.methodology.domain.entities

import java.util.UUID

//Historia 1: Crear el modelo básico de usuario
//Crear un modelo de usuario con los atributos uuid, nombre, username, email, y contraseña.
//El uuid deberá ser de tipo autoincremental y único.
//El modelo deberá tener carácter de Entidad, se utilizará para realizar consultas a la base de datos.

class Usuario (
    val uuid: String = UUID.randomUUID().toString(),
    var nombre: String,
    var username: String,
    var email: String,
    var contraseña: String,
)

