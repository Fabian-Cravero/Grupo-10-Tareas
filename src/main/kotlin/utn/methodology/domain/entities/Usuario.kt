package utn.methodology.domain.entities

import java.util.UUID

//Historia 1: Crear el modelo básico de usuario
//Crear un modelo de usuario con los atributos uuid, nombre, username, email, y contraseña.
//El uuid deberá ser de tipo autoincremental y único.
//El modelo deberá tener carácter de Entidad, se utilizará para realizar consultas a la base de datos.

class Usuario (
    private val uuid: String = UUID.randomUUID().toString(),
    private var nombre: String,
    private var username: String,
    private var email: String,
    private var contraseña: String,
)

