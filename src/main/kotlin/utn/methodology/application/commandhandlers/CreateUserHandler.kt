package utn.methodology.application.commandhandlers

import utn.methodology.application.commands.CreateUserCommand
import utn.methodology.domain.entities.Usuario
import utn.methodology.infrastructure.persistence.UserMongoRepository
import java.util.UUID

class CreateUserHandler (
    private val userRepository: UserMongoRepository
    ) {
        fun handle(command: CreateUserCommand) {
            val user = Usuario(
                UUID.randomUUID().toString(),
                command.username,
                command.email,
                command.password
            )
            userRepository.save(user)
        }
    }

