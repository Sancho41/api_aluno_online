package dev.panelinha.dev.panelinha.aonline.dao

import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import org.litote.kmongo.KMongo
import org.litote.kmongo.getCollection

class DAO {
    val database: MongoDatabase
    init {
        val client = KMongo.createClient("mongodb+srv://panelinha:123456_panelinha@cluster0.hyekv.mongodb.net")
        this.database = client.getDatabase("aonline-teste")
    }

    inline fun <reified T : Any> getCollection(): MongoCollection<T> {
        return this.database.getCollection<T>()
    }
}