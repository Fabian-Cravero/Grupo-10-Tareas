package utn.methodology.domain.entities

class Post(
    val uuid:String,
    val uuidUser:String,
    var text: String,
    var date: String,
) {
    fun getIdPost(): String {
        return this.uuid;
    }
    companion object {
        fun fromPrimitives(primitives: Map<String, String>): Post {

            val post = Post(
                primitives["uuid"] as String,
                primitives["uuidUser"] as String,
                primitives["text"] as String,
                primitives["date"] as String
            );
            return post;
        }
    }
    fun toPrimitives(): Map<String, String> {
        return mapOf(
            "uuid" to this.uuid,
            "uuidUser" to this.uuidUser,
            "text" to this.text,
            "date" to this.date
        )
    }
    fun validetDate(date:String):Boolean{
        val regex = Regex("""\d{4}-\d{2}-\d{2}""")
        return regex.matches(date)
    }
}
