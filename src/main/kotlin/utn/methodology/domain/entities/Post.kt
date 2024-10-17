package utn.methodology.domain.entities

class Post(
    val uuid:String,
    val uuidUser:String,
    var query: String
) {
    fun getIdUser(): String {
        return this.uuid;
    }
    companion object {
        fun fromPrimitives(primitives: Map<String, String>): Post {

            val post = Post(
                primitives["uuid"] as String,
                primitives["uuidUser"] as String,
                primitives["query"] as String,
            );
            return post;
        }
    }
    fun toPrimitives(): Map<String, String> {
        return mapOf(
            "uuid" to this.uuid,
            "uuidUser" to this.uuidUser,
            "username" to this.query,
        )
    }
    fun getId(): String {
        return this.uuid;
    }
}