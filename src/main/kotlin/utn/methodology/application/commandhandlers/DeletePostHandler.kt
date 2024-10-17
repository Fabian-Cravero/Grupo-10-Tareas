package utn.methodology.application.commandhandlers

import io.ktor.server.plugins.*
import utn.methodology.application.commands.DeletePostCommand
import utn.methodology.domain.entities.Post
import utn.methodology.infrastructure.persistence.PostMongoRepository

class DeletePostHandler (
    private val PostRepository: PostMongoRepository
) {

    fun handle(command: DeletePostCommand) {

        val Post = PostRepository.findOne(command.id)

        if (Post == null) {
            throw NotFoundException("not found Post with id: ${command.id}")
        }

        PostRepository.delete(Post);
    }
}