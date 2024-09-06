package utn.methodology.domain.entities

import utn.methodology.domain.entities.Usuario

interface UserRepository {
    fun save(user: Usuario)
    fun findOne(id: String): Usuario?
}