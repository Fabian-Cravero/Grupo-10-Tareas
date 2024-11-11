package utn.methodology.application.queries

data class FindPostByIdQuery (
        val id: String
){

    fun validate() : FindPostByIdQuery {
        checkNotNull(id) {throw IllegalArgumentException("IdUser must be defined")}
        return this
    }
}