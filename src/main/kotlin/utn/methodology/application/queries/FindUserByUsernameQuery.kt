package utn.methodology.application.queries

class FindUserByUsernameQuery(val userName: String) {
    init {
        require(userName.isNotBlank()) { "El nombre de usuario no puede estar vacio" }
    }
}
