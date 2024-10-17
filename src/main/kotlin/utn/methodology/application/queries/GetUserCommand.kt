package utn.methodology.application.queries

class GetUserCommand (
    val username: String
) {

    fun validate(): GetUserCommand {
        checkNotNull(username) { throw IllegalArgumentException("Username must be defined") }
        return this;
    }
}