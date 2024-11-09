package utn.methodology.application.commands

class unFollowUserCommand(
    val uuid:String,
    val uuidUser:String,
    var username: String,

) {
    fun validate(): unFollowUserCommand {
        checkNotNull(uuid) {throw IllegalArgumentException("Id not found")}
        checkNotNull(uuidUser) {throw IllegalArgumentException("Id not found")}
        checkNotNull(username) { throw IllegalArgumentException("Name must be defined")}
        return this;
    }
}