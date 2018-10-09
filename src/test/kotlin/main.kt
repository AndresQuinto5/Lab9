import   com.github.kittinunf.fuel.Fuel
import kotlin.concurrent.thread
import dbmodels.Song
import models.song
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

fun main(args: Array<String>) {
    val url = "https://next.json-generator.com/api/json/get/EkeSgmXycS"

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
    //Menus

    fun menu1(): String {
        return """
        Menu Principal
        1. Buscar canciones por nombre
        2. Buscar canciones por artista
        3. Mostrar todas mis canciones favoritas
        4. Salir
        """.trimIndent()
    }

    fun menu2(): String {
        return """
        Menu 2
        Ingrese nombre de la cancion:
        """.trimIndent()

    }

    fun menufav(): String {
        return """
        Sus canciones favoritas son:
        """".trimIndent()
    }
    //Funciones

    fun SongsperName(val nombre: String) {
        var lista: String
        var selecion: Boolean = true
        var respuesta: String
        var respuesta2: Int
        var num: Int = 0
        for (song in Song.selectAll()) {
            if (song.name == nombre) {
                num++
                println("$num. ${song.name}")
            }
        }
        println("¿Desea guardar alguna canción como favorita? Si o No")

        while (selecion) {
            respuesta = readLine()!!
            when (respuesta) {
                "Si" -> {
                    println("Elija la cancion (ingrese el numero)")
                    respuesta2 = readLine()!!.toInt()
                    var num2: Int = 0
                    for (song in Song.selectAll()) {
                        num2++
                        if (song.name == nombre) {
                            num2++
                            println("$num. ${song.name}")
                        }
                        if (num2 == respuesta2) {
                            song.isFavorite = true
                        }
                    }
                    selecion = false
                }
                "Nop" -> {
                    selecion = false
                }
                else -> println("invalido, intente de nuevo.")
            }
        }
    }

    fun favSongs() {
        var lista: String
        var num: Int = 0
        for (song in Song.selectAll()) {
            num++
            if (song.isFavorite == true)
                println("$num. ${song.name}")
        }
    }
    var on : Boolean = true
    var men: Int
    var nombre: String
    println(menu1())
    men = readLine()!!.toInt()
    while(on) {
        when (men) {
            1 -> {
                println(menu2())
                nombre = readLine()!!
                SongsperName(nombre)
            }
            2 -> {
                println(menufav())
                favSongs()
            }
            3 -> {
                println("Saliendo del programa")
                on = false
            }
            else -> println("Opcion invalida, intente de nuevo.")
        }
    }
}