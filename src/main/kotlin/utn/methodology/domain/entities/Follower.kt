package utn.methodology.domain.entities

class Follower (
    val uuid: String,
    val user: String){
    companion object {
        fun fromPrimitives(primitives: Map<String, String>): Follower {
            val follower = Follower(
                primitives["uuid"] as String,
                primitives["user"] as String,
            );
            return follower;
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