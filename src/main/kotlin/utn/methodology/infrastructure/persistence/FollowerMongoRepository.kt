package utn.methodology.infrastructure.persistence

import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import org.bson.Document
import utn.methodology.domain.entities.Follower

class FollowerMongoRepository (private val database: MongoDatabase) {
    private var followers: MongoCollection<Any>;

    init {
        followers = database.getCollection("follow") as MongoCollection<Any>;
    }
    fun findByUser(user: String): Follower? {
        val filter = Document("follow", user);
        val primitives = followers.find(filter).firstOrNull();
        if (primitives == null) {
            return null;
        }
        return Follower.fromPrimitives(primitives as Map<String, String>)
    }
    fun follower (follower: Follower){
        followers.insertOne(follower)
    }

    fun unFollower(follow: Follower) {
        val filter = Document("uuid", follow.getIdUser());
        followers.deleteOne(filter)
    }
    fun findAll(): List<Follower> {

        val primitives = followers.find().map { it as Document }.toList();

        return primitives.map {
            Follower.fromPrimitives(it.toMap() as Map<String, String>)
        };
    }
//    fun quantity (): Int{
//        val primitives = followers.find().map { it as Document }.toList();
//        val quant:Int = primitives.size
//        return quant
//    }
}