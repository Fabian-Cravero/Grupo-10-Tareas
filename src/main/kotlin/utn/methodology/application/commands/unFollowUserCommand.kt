package utn.methodology.application.commands

class unFollowUserCommand(
    val uuid:String,
    var username: String,

) {
    fun validate(): unFollowUserCommand {
        checkNotNull(username) { throw IllegalArgumentException("Name must be defined") }
        checkNotNull(uuid) {throw IllegalArgumentException("Id not found")}
        return this;
    }
}