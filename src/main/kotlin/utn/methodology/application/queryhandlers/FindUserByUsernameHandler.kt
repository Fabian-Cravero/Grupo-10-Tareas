package utn.methodology.application.handlers

import utn.methodology.application.queries.FindUserByUsernameQuery
import utn.methodology.infrastructure.persistence.UserMongoRepository
import io.ktor.server.plugins.NotFoundException

class FindUserByUsernameHandler(
    private val userRepository: UserMongoRepository
) {

    fun handle(query: FindUserByUsernameQuery): Map<String, String> {
        val user = userRepository.findByUsername(query.userName)
            ?: throw NotFoundException("Usuario con el nombre de usuario '${query.userName}' no encontrado")
        return user.toPrimitives()
    }
}
