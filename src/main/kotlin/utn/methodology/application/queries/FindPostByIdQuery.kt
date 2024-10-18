package utn.methodology.application.queries

data class FindPostByIdQuery (
        val id: String
){

    fun validate() : FindPostByIdQuery {
        checkNotNull(id) {throw IllegalArgumentException("Id must be defined")}
        return this
    }
}