//package utn.methodology.infrastructure.http.actions
//
//import utn.methodology.application.commands.GetPostCommand
//import utn.methodology.application.commands.GetUserCommand
//import utn.methodology.domain.entities.Post
//
//class GetPostAction(private val handler : GetPostHandler) {
//
//    fun execute(query: GetPostCommand): Map<String, String> {
//        query
//            .validate()
//            .let {
//                return handler.handle(it)
//            }
//    }
//}