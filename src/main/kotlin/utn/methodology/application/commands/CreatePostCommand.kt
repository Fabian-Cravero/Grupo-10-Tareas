package utn.methodology.application.commands


class CreatePostCommand(
    val uuidUser: String,
    var text:String,
    var date:String
) {
    fun validate(): CreatePostCommand {
        checkNotNull(uuidUser) { throw IllegalArgumentException("User Id does not exist") }
        checkNotNull(text) { throw IllegalArgumentException("text must be defined correctly") }
        checkNotNull(date) {throw IllegalArgumentException("date must be defined correctly")}
        return this;
    }
}
