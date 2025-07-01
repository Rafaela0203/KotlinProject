import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
data class LayerData(
    val length: String,
    val score: String
)

@Entity(tableName = "evaluations")
@Serializable
data class EvaluationData(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val sessionId: Long,
    val sampleName: String,
    val location: String,
    val evaluator: String,
    val layers: List<LayerData>,
    val otherImportantInfo: String,
    val imagePath: String? = null
)