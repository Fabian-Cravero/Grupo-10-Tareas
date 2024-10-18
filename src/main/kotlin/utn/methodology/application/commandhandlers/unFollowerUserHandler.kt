package utn.methodology.application.commandhandlers

import utn.methodology.application.commands.unFollowerUserCommand
import utn.methodology.domain.entities.Follower
import utn.methodology.infrastructure.persistence.FollowerMongoRepository

class unFollowerUserHandler(
    private val userRepository:FollowerMongoRepository
) {
    fun handle(command:unFollowerUserCommand):unFollowerUserHandler{

        val unfollower = Follower(
            command.uuid,
            command.username
        )
        if (userRepository.findByUser(command.username) == null) {
            userRepository.unFollower(unfollower);
        }else{
            throw IllegalArgumentException("Do not follow this user")
        }
        return this;
    }
}