//package utn.methodology.infrastructure.http.actions
//
//import utn.methodology.application.commands.GetUserCommand
//
//class GetUserAction(private val handler: GetUserHandler) {
//
//    fun execute(query: GetUserCommand): Map<String, String> {
//        query
//            .validate()
//            .let {
//                return handler.handle(it)
//            }
//
//    }
//}