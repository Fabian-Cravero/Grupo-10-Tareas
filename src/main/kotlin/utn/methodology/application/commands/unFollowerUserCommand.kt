package utn.methodology.application.commands

class unFollowerUserCommand (
    val uuid:String,
    val uuidUser:String,
    var username: String
) {
    fun validate(): unFollowerUserCommand {
        checkNotNull(uuid) {throw IllegalArgumentException("Id not found")}
        checkNotNull(uuidUser) {throw IllegalArgumentException("Id not found")}
        checkNotNull(username) { throw IllegalArgumentException("Name must be defined")}
        return this;
    }
}