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
            command.uuidUser,
            command.username
        )
        userRepository.follow(follow)
    }
}