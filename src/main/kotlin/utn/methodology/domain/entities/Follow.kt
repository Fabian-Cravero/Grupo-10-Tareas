package utn.methodology.domain.entities

class Follow (
    val uuid: String,
    val user: String,
    val following: List<String>){

    companion object {
        fun fromPrimitives(primitives: Map<String, String>): Follow {
            val follow = Follow(
                primitives["uuid"] as String,
                primitives["user"] as String,
                following = primitives["following"] as List<String>
            );
            return follow;
        }
    }
    fun toPrimitives(): Map<String, Any> {
        return mapOf(
            "uuid" to this.uuid,
            "user" to this.user,
            "following" to this.following
        )
    }
    fun getIdUser(): String {
        return this.uuid;
    }
}