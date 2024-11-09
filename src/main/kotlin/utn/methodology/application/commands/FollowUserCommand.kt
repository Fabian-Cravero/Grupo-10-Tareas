package utn.methodology.application.commands

import kotlinx.serialization.Serializable

@Serializable()
class FollowUserCommand(
    val uuidUser:String,
    val username: String
) {
    fun validate(): FollowUserCommand {
        checkNotNull(uuidUser) {throw IllegalArgumentException("Id not found")}
        checkNotNull(username) { throw IllegalArgumentException("Name must be defined")}
        return this;
    }
}