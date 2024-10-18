package utn.methodology.application.commands

class GetPostCommand(
        val userId: String
) {
        fun validate(): GetPostCommand {
                checkNotNull(userId) { throw IllegalArgumentException("The user with post must be defined") }
                return this;
        }
}