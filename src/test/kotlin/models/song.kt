package models

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

data class song(val year: Int, val country: String, val region: String, val artistName: String, val song: String, val artistGender: String, val groupOrSolo: String, val place: String, val points: String, val isFinal: String, val isSonginEnglish: String, val songQuality: String, val normalizedPoints: String, val energy: String, val duration: String, val acousticness: String, val danceability: String, val tempo: String, val speechiness: String, val key: String, val liveness: String, val timeSignature: String, val mode: String, val loudness: String, val valence: String, val happiness:String, val isFav : Boolean)  {
    class songArrayDeserializer: ResponseDeserializable<Array<song>> {
        override fun deserialize(content: String): Array<song>? {
            return Gson().fromJson(content, Array<song>::class.java)
        }
    }
}

