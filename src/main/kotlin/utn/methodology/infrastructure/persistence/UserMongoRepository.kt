package utn.methodology.infrastructure.persistence

import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.UpdateOptions
import org.bson.Document
import utn.methodology.domain.entities.Post
import utn.methodology.domain.entities.Usuario

open class UserMongoRepository(private val database: MongoDatabase) {

    private var collection: MongoCollection<Any>

    init {
        collection = database.getCollection("users") as MongoCollection<Any>
    }

    fun save(user: Usuario) {
        val options = UpdateOptions().upsert(true)
        val filter = Document("_uuid", user.getIdUser())
        val update = Document("\$set", user.toPrimitives())
        collection.updateOne(filter, update, options)
    }

    fun findOne(id: String): Usuario? {
        val filter = Document("_uuid", id)
        val primitives = collection.find(filter).firstOrNull()
        return primitives?.let { Usuario.fromPrimitives(it as Map<String, String>) }
    }

    fun findAll(): List<Usuario> {
        val primitives = collection.find().map { it as Document }.toList();
        return primitives.map {
            Usuario.fromPrimitives(it.toMap() as Map<String, String>)
        };
    }

    fun findByUsername(username: String): Usuario? {
        val filter = Document("username", username)
        val primitives = collection.find(filter).firstOrNull()
        return primitives?.let { Usuario.fromPrimitives(it as Map<String, String>) }
    }

    fun findByEmail(email: String): Usuario? {
        val filter = Document("email", email)
        val primitives = collection.find(filter).firstOrNull()
        return primitives?.let { Usuario.fromPrimitives(it as Map<String, String>) }
    }

}
