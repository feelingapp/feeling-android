package app.getfeeling.feeling.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import app.getfeeling.feeling.room.entities.Feeling
import org.threeten.bp.OffsetDateTime

@Dao
interface FeelingDao {
    @Insert
    suspend fun insert(feeling: Feeling)

    @Delete
    suspend fun delete(feeling: Feeling)

    @Query(
        """
       SELECT * 
       FROM feelings 
       WHERE datetime(created_at) BETWEEN :monthStart and :monthEnd
       ORDER BY datetime(created_at) DESC 
    """
    )
    fun feelingsByDate(
        monthStart: OffsetDateTime,
        monthEnd: OffsetDateTime
    ): LiveData<List<Feeling>>
}
