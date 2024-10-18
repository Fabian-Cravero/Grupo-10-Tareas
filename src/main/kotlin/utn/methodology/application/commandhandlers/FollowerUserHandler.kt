package utn.methodology.application.commandhandlers

import utn.methodology.application.commands.FollowerUserCommand
import utn.methodology.domain.entities.Follower
import utn.methodology.infrastructure.persistence.FollowerMongoRepository
import java.util.*

class FollowerUserHandler (
    private val userRepository: FollowerMongoRepository
) {
    fun handle(command: FollowerUserCommand){
        val follower = Follower(
            UUID.randomUUID().toString(),
            command.username
        )
        if (userRepository.findByUser(follower.user) == null) {
            userRepository.follower(follower);
        }else{
            throw IllegalArgumentException("You already follow this user")
        }
    }
}