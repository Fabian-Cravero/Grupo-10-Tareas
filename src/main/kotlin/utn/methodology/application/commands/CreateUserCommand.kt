package utn.methodology.application.commands

import kotlinx.serialization.Serializable

@Serializable()

class CreateUserCommand(
    val username: String,
    val email: String,
    val password: String
) {
    fun validate(): CreateUserCommand {
        checkNotNull(username) { throw IllegalArgumentException("Username must be defined") }
        checkNotNull(email) { throw IllegalArgumentException("Email must be defined") }
        checkNotNull(password) { throw IllegalArgumentException("Password must be defined") }
        return this;
    }
}