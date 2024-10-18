package utn.methodology.application.commandhandlers

import utn.methodology.application.commands.unFollowUserCommand
import utn.methodology.domain.entities.Follow
import utn.methodology.infrastructure.persistence.FollowMongoRepository

class unFollowUserHandler(
    private val userRepository:FollowMongoRepository
) {
    fun handle(command:unFollowUserCommand):unFollowUserHandler{

        val unfollow = Follow(
            command.uuid,
            command.username
        )
        if (userRepository.findByUser(command.username) == null) {
            userRepository.unFollow(unfollow);
        }else{
            throw IllegalArgumentException("Do not follow this user")
        }
        return this;
    }
}