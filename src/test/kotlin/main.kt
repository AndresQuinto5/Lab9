import com.github.kittinunf.fuel.Fuel
import kotlin.concurrent.thread
import dbmodels.Song
import models.song
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

fun main(args: Array<String>) {
 val url= "https://next.json-generator.com/api/json/get/EkeSgmXycS"

    val (request, response, result) = Fuel.get(url).responseObject(song.songArrayDeserializer())
    val (persons, err) = result
    persons?.forEach { println(it) }

    Database.connect(
            "jdbc:postgresql:misctests",
            "org.postgresql.Driver",
            "postgres",
            "postgres"
    )

    Thread.sleep(5000)

}
