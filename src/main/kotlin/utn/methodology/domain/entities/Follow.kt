package utn.methodology.domain.entities

class Follow (
    val uuid: String,
    val uuidUser: String,
    val username: String,
){

    companion object {
        fun fromPrimitives(primitives: Map<String, String>): Follow {
            val follow = Follow(
                primitives["uuid"] as String,
                primitives["uuidUser"] as String,
                primitives["username"] as String
            );
            return follow;
        }
    }
    fun toPrimitives(): Map<String, Any> {
        return mapOf(
            "uuid" to this.uuid,
            "uuidUser" to this.uuidUser,
            "user" to this.username,
        )
    }
    fun getIdUser(): String {
        return this.uuidUser;
    }
}