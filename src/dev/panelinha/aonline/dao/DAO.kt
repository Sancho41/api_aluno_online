package dev.panelinha.dev.panelinha.aonline.dao

import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import org.litote.kmongo.KMongo
import org.litote.kmongo.getCollection

open class DAO {
    val database: MongoDatabase
    private val username: String = "panelinha"
    private val password: String = "panelinha123"
    private val host: String = "cluster0.1xdse.mongodb.net"
    // TODO: Recuperar dados por variáveis de ambiente
    init {
        val client = KMongo.createClient("mongodb+srv://$username:$password@$host")
        this.database = client.getDatabase("aonline-teste")
    }

    inline fun <reified T : Any> getCollection(): MongoCollection<T> {
        return this.database.getCollection<T>()
    }
}
