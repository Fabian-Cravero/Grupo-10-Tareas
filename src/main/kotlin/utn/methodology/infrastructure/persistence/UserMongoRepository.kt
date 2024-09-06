package utn.methodology.infrastructure.persistence

import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.UpdateOptions
import example.com.app.domain.contracts.ShippingRepository
import example.com.app.domain.entity.Shipping
import io.github.cdimascio.dotenv.dotenv
import org.bson.Document
import utn.methodology.domain.entities.Usuario
import kotlin.math.truncate

val collectionName: String = dotenv()["USER_COLLECTION_NAME"] ?: "User"

class UserMongoRepository(private val database: MongoDatabase) : UserRepository {

    private var collection:MongoCollection<Any>;

    init {
        collection = this.database.getCollection(collectionName) as MongoCollection<Any>;
    }

    override fun save(user: Usuario) {
        println("ShippingMongoRepository - Saving shipping: $user")
        val options = UpdateOptions().upsert(true);

        val filter = Document("_Id", user.)
        val update = Document("\$set", user.toPrimitives())
    }

}