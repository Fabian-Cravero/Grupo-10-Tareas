package utn.methodology.infrastructure.http.actions
import utn.methodology.application.commands.FollowUserCommand
import utn.methodology.application.commandhandlers.FollowUserHandler

class FollowUserAction(private val handler: FollowUserHandler) {
    fun execute(body: FollowUserCommand) {

        body.validate().let {
            handler.handle(it)
        }
    }
}