package utn.methodology.domain.entities

class Post(
    val uuid: String,
    val uuidUser: String,
    // val userId: String,
    var text: String,
    var date: String,
    var createdAt: String
) {
    fun getIdPost(): String {
        return this.uuid
    }

    companion object {
        fun fromPrimitives(primitives: Map<String, String>): Post {
            return Post(
                uuid = primitives["uuid"] as String,
                uuidUser = primitives["uuidUser"] as String,
                // userId = primitives["userId"] as String,
                text = primitives["text"] as String,
                date = primitives["date"] as String,
                createdAt = primitives["createdAt"] as String
            )
        }
    }

    fun toPrimitives(): Map<String, String> {
        return mapOf(
            "uuid" to this.uuid,
            "uuidUser" to this.uuidUser,
            // "userId" to this.userId,
            "text" to this.text,
            "date" to this.date,
            "createdAt" to this.createdAt
        )
    }

    fun validateDate(date: String): Boolean {
        val regex = Regex("""\d{4}-\d{2}-\d{2}""")
        return regex.matches(date)
    }
}
