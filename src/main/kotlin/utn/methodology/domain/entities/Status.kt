package utn.methodology.domain.entities

open class Status (open val status: String, open val substatus: String){
    companion object {
        fun fromPrimitives(any: Map<String, String>?): Status? {
            if(any== null) {
                return null
            }

            return Status(any["status"] as String, any["substatus"] as String)
        }
    }
}