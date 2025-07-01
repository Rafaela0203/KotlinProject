import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Converters {
    @TypeConverter
    fun fromLayerDataList(value: List<LayerData>): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toLayerDataList(value: String): List<LayerData> {
        return Json.decodeFromString(value)
    }
}