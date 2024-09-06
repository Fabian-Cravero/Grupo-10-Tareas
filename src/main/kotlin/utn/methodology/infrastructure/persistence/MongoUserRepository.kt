package utn.methodology.infrastructure.persistence

import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.UpdateOptions
import utn.methodology.domain.entities.Usuario
import org.bson.Document

    class MongoUserRepository(private val database: MongoDatabase) {

        private var collection: MongoCollection<Any>;

        init {
            collection = database.getCollection("usuario") as MongoCollection<Any>;
        }

        fun save(user: Usuario) {
            val options = UpdateOptions().upsert(true);

            val filter = Document("_uuid", user.getId())
            val update = Document("\$set", user.toPrimitives())

            collection.updateOne(filter, update, options)
        }

        fun findOne(id: String): Usuario? {
            val filter = Document("_id", id);

            val primitives = collection.find(filter).firstOrNull();

            if (primitives == null) {
                return null;
            }

            return Usuario.fromPrimitives(primitives as Map<String, String>)
        }

        fun findAll(): List<Usuario> {

            val primitives = collection.find().map { it as Document }.toList();

            return primitives.map {
                Usuario.fromPrimitives(it.toMap() as Map<String, String>)
            };
        }

        fun delete(user: Usuario) {
            val filter = Document("_id", user.getId());

            collection.deleteOne(filter)
        }
    }
