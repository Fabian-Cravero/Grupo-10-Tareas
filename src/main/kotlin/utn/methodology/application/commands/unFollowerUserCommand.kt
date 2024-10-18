package utn.methodology.application.commands

class unFollowerUserCommand (
    val uuid:String,
    var username: String,
) {
    fun validate(): unFollowerUserCommand {
        checkNotNull(username) { throw IllegalArgumentException("Name must be defined") }
        checkNotNull(uuid) {throw IllegalArgumentException("Id not found")}
        return this;
    }
}