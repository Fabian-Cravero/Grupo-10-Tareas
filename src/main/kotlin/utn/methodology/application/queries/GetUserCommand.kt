package utn.methodology.application.commands

class GetUserCommand (
    val username: String
) {

    fun validate(): GetUserCommand {
        checkNotNull(username) { throw IllegalArgumentException("Username must be defined") }
        return this;
    }
}