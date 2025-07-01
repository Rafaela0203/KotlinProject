import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface EvaluationDao {
    @Insert
    suspend fun insert(evaluation: EvaluationData)

    @Query("SELECT * FROM evaluations")
    suspend fun getAll(): List<EvaluationData>

    @Query("SELECT * FROM evaluations WHERE id = :id")
    suspend fun getById(id: Int): EvaluationData?
}