package utn.methodology.infrastructure.persistence

import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import org.bson.Document
import utn.methodology.domain.entities.Follow

class FollowMongoRepository(private val database: MongoDatabase) {
    private var follows: MongoCollection<Any>;

    init {
        follows = database.getCollection("follow") as MongoCollection<Any>;
    }
    fun findByUser(user: String): Follow? {
        val filter = Document("follow", user);
        val primitives = follows.find(filter).firstOrNull();
        if (primitives == null) {
            return null;
        }
        return Follow.fromPrimitives(primitives as Map<String, String>)
    }
    fun follow (follow: Follow){
        follows.insertOne(follow)
    }

    fun unFollow(follow:Follow) {
        val filter = Document("uuid", follow.getIdUser());
        follows.deleteOne(filter)
    }
    fun findAll(): List<Follow> {

        val primitives = follows.find().map { it as Document }.toList();

        return primitives.map {
            Follow.fromPrimitives(it.toMap() as Map<String, String>)
        };
    }
//    fun quantity (): Int{
//        val primitives = follows.find().map { it as Document }.toList();
//        val quant:Int = primitives.size
//        return quant
//    }
}