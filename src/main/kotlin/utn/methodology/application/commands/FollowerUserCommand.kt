package utn.methodology.application.commands


class FollowerUserCommand (
    val uuid:String,
    var username: String,
    )
{
    fun validate(): FollowerUserCommand {
        checkNotNull(username) { throw IllegalArgumentException("Name must be defined") }
        checkNotNull(uuid) {throw IllegalArgumentException("Id not found")}
        return this;
    }
}