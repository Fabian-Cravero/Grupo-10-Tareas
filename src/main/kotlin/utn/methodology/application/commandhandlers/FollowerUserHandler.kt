package utn.methodology.application.commandhandlers

import utn.methodology.application.commands.FollowerUserCommand
import utn.methodology.domain.entities.Follow
import utn.methodology.infrastructure.persistence.FollowMongoRepository
import java.util.*

class FollowerUserHandler (
    private val userRepository: FollowMongoRepository
) {
    fun handle(command: FollowerUserCommand){
        val follow = Follow(
            UUID.randomUUID().toString(),
            command.username
        )
        if (userRepository.findByUser(follow.user) == null) {
            userRepository.follow(follow);
        }else{
            throw IllegalArgumentException("You already follow this user")
        }
    }
}