package utn.methodology.application.commands

<<<<<<< HEAD
class DeleteUserCommand(
    val id: String
) {
=======
class DeleteUserCommand (
    val id: String
){
>>>>>>> cb9f3823485dc2326ff95e6629540162570f0b4b
    fun validate(): DeleteUserCommand {
        checkNotNull(id) { throw IllegalArgumentException("Id must be defined") }
        return this;
    }
<<<<<<< HEAD
=======


>>>>>>> cb9f3823485dc2326ff95e6629540162570f0b4b
}