package utn.methodology.application.commands


class CreatePostCommand(
    val uuidUser: String,
    var query:String
) {
    fun validate(): CreatePostCommand {
        checkNotNull(uuidUser) { throw IllegalArgumentException("User Id does not exist") }
        checkNotNull(query) { throw IllegalArgumentException("query must be defined correctly") }
        return this;
    }
}