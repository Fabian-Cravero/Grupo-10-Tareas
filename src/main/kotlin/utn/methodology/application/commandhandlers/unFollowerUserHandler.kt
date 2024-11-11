package utn.methodology.application.commandhandlers

import utn.methodology.application.commands.unFollowerUserCommand
import utn.methodology.domain.entities.Follow
import utn.methodology.infrastructure.persistence.FollowMongoRepository

class unFollowerUserHandler(
    private val userRepository:FollowMongoRepository
) {
    fun handle(command:unFollowerUserCommand):unFollowerUserHandler{

        val unfollower = Follow(
            command.uuid,
            command.uuidUser,
            command.username
        )
        if (userRepository.findByUser(command.username) == null) {
            userRepository.unFollow(unfollower);
        }else{
            throw IllegalArgumentException("Do not follow this user")
        }
        return this;
    }
}