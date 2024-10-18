package utn.methodology.infrastructure.persistence

import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.Sorts
import com.mongodb.client.model.UpdateOptions
import org.bson.Document
import utn.methodology.domain.entities.Post
import utn.methodology.domain.entities.Usuario


class PostMongoRepository(private val database: MongoDatabase) {

    private var collection: MongoCollection<Any>;

    init {
        collection = database.getCollection("posts") as MongoCollection<Any>;
    }
    fun save (post:Post){
        val options = UpdateOptions().upsert(true);
        val filter = Document("uuid", post.getIdUser())
        val update = Document("\$set", post.toPrimitives())
        collection.updateOne(filter, update, options)
    }

    //------
    fun findAll() : List<Post> {
        val primitives = collection.find().map { it as Document }.toList();

        return primitives.map {
            Post.fromPrimitives(it.toMap() as Map<String, String>)
        };
    }

    fun findAllOrder(order: String, limit: Int, offset: Int): List<Post> {
        val collection = database.getCollection("posts", Post::class.java)

        val sort = if (order == "DESC") Sorts.descending("creationDate") else Sorts.ascending("creationDate")

        return collection.find()
            .sort(sort)
            .skip(offset)
            .limit(limit)
            .toList()
    }
}