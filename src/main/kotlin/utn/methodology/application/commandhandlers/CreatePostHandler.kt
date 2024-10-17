package utn.methodology.application.commandhandlers

import utn.methodology.application.commands.CreatePostCommand
import utn.methodology.domain.entities.Post
import utn.methodology.infrastructure.persistence.PostMongoRepository
import java.util.UUID

class CreatePostHandler (
    private val userRepository: PostMongoRepository
) {
    fun handle(command: CreatePostCommand) {
        val post = Post(
            UUID.randomUUID().toString(),
            command.uuidUser,
            command.query
        )

        userRepository.save(post)
    }
}

