package utn.methodology.domain.entities

class Follow (
    val uuid: String,
    val user: String){
    companion object {
        fun fromPrimitives(primitives: Map<String, String>): Follow {
            val follow = Follow(
                primitives["uuid"] as String,
                primitives["user"] as String,
            );
            return follow;
        }
    }
    fun toPrimitives(): Map<String, String> {
        return mapOf(
            "uuid" to this.uuid,
            "user" to this.user
        )
    }
    fun getIdUser(): String {
        return this.uuid;
    }
}