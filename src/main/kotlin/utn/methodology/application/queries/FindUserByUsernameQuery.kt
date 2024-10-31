package utn.methodology.application.queries

class FindUserByUsernameQuery(val userName: String) {
    fun validate() : FindUserByUsernameQuery {
        require(userName.isNotBlank()) { "El nombre de usuario no puede estar vacio" }
        return this
    }
}
