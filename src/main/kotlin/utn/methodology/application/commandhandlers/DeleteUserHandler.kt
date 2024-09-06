package utn.methodology.application.commandhandlers

import utn.methodology.application.commands.DeleteUserCommand
import io.ktor.server.plugins.*
import utn.methodology.infrastructure.persistence.MongoUserRepository

class DeleteUserHandler (
    private val userRepository: MongoUserRepository
    ) {

        fun handle(command: DeleteUserCommand) {

            val user = userRepository.findOne(command.id)

            if (user == null) {
                throw NotFoundException("not found user with id: ${command.id}")
            }

            userRepository.delete(user);
        }
}