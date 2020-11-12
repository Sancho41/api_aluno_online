package dev.panelinha.aonline.dao

import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import org.litote.kmongo.KMongo
import org.litote.kmongo.getCollection

open class DAO {
    val database: MongoDatabase
    private val username: String = System.getenv("DB_USERNAME") ?: ""
    private val password: String = System.getenv("DB_PASSWORD") ?: ""
    private val host: String = System.getenv("DB_HOST") ?: ""
    private val dbname: String = System.getenv("DB_NAME") ?: "aonline-teste"
    // TODO: Recuperar dados por variáveis de ambiente
    init {
        val client = KMongo.createClient("mongodb+srv://$username:$password@$host")
        this.database = client.getDatabase(this.dbname)
    }

    inline fun <reified T : Any> getCollection(): MongoCollection<T> {
        return this.database.getCollection<T>()
    }
}
