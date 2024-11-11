package utn.methodology.application.commands
import kotlinx.serialization.Serializable

@Serializable()

class FollowerUserCommand (
    val uuidUser:String,
    var username: String,
    )
{
    fun validate(): FollowerUserCommand {
        checkNotNull(uuidUser) {throw IllegalArgumentException("Id not found")}
        checkNotNull(username) { throw IllegalArgumentException("Name must be defined")}
        return this;
    }
}