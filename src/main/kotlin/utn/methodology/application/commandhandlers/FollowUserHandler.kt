package utn.methodology.application.commandhandlers

import utn.methodology.application.commands.FollowUserCommand
import utn.methodology.domain.entities.Follow
import utn.methodology.infrastructure.persistence.FollowMongoRepository
import java.util.UUID

class FollowUserHandler(
    private val userRepository:FollowMongoRepository
) {
    fun handle(command:FollowUserCommand){
        val follow = Follow(
            UUID.randomUUID().toString(),
            command.uuidUser,
            command.username
        )
        userRepository.follow(follow)
    }
}