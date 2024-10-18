package utn.methodology.application.commands

class FollowUserCommand(
    val username: String
) {
    fun validate(): FollowUserCommand {
        checkNotNull(username) { throw IllegalArgumentException("Name must be defined") }
        return this;
    }
}