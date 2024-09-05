package utn.methodology.application.commands

class CreateUserCommand (
    val name: String,
    val username: String,
    val email: String,
    val password: String,
<<<<<<< HEAD
){
=======
) {
>>>>>>> cb9f3823485dc2326ff95e6629540162570f0b4b
    fun validate(): CreateUserCommand {
        checkNotNull(name) { throw IllegalArgumentException("Name must be defined") }
        checkNotNull(username) { throw IllegalArgumentException("Username must be defined") }
        checkNotNull(email) { throw IllegalArgumentException("Email must be defined") }
        checkNotNull(password) { throw IllegalArgumentException("Password must be defined") }
        return this;
    }
}