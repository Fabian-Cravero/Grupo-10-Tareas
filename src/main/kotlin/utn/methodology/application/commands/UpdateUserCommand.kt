package utn.methodology.application.commands

class UpdateUserCommand(
    val name: String,
    val username: String,
    val email: String,
    val password: String,
) {
    var id: String = ""
    fun validate() : UpdateUserCommand {
        checkNotNull(id) { throw IllegalArgumentException("Id must be defined") }
        check(id.isNotEmpty()) { throw IllegalArgumentException("Id must be defined") }
        checkNotNull(name) { throw IllegalArgumentException("Name must be defined") }
        checkNotNull(username) { throw IllegalArgumentException("Username must be defined") }
        checkNotNull(email) { throw IllegalArgumentException("Email must be defined") }
        checkNotNull(password) { throw IllegalArgumentException("Password must be defined") }

        return this;
    }
}