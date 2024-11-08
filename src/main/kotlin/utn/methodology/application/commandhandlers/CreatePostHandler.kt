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
            command.text,
            command.date,
            command.createdAt)
        if(post.text.length>100 ){
            if(post.validateDate(post.date)){
                userRepository.save(post)
            }else{
                throw IllegalArgumentException("date must be defined correctly YYYY-MM-DD")
            }
        }else{
            throw IllegalArgumentException("You reached the character limit")
        }
    }

}

