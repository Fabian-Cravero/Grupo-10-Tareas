package utn.methodology.infrastructure.persistence

import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.UpdateOptions
import org.bson.Document
import utn.methodology.domain.entities.Post


class PostMongoRepository(private val database: MongoDatabase) {

    private var collection: MongoCollection<Any>;

    init {
        collection = database.getCollection("posts") as MongoCollection<Any>;
    }
    fun save (post:Post){
        val options = UpdateOptions().upsert(true);
        val filter = Document("_uuid", post.getIdUser())
        val update = Document("\$set", post.toPrimitives())
        collection.updateOne(filter, update, options)
    }
}