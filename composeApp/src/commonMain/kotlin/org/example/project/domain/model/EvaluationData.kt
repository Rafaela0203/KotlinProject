// Assegure-se de que esta importação está presente
import kotlinx.serialization.Serializable

@Serializable
data class LayerData(
    val length: String,
    val score: String
)

@Serializable
data class EvaluationData(
    val sampleName: String,
    val location: String,
    val evaluator: String,
    val layers: List<LayerData>,
    val otherImportantInfo: String
)