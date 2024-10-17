package utn.methodology.infrastructure.http.actions

import utn.methodology.application.commandhandlers.DeletePostHandler
import utn.methodology.application.commands.DeletePostCommand
import utn.methodology.infrastructure.http.actions.DeletePostAction

class DeletePostAction (
    private val handler: DeletePostHandler
) {

    fun execute(command: DeletePostCommand) {
        command.validate().let {
            handler.handle(it)
        }
    }
}