package co.feelingapp.feeling.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import co.feelingapp.feeling.room.entities.Feeling

@Dao
interface FeelingDao {
    @Insert
    fun insert(feeling: Feeling)

    @Delete
    fun delete(feeling: Feeling)

    @Query("SELECT * FROM feelings ORDER BY datetime(created_at) DESC ")
    fun feelingsByDate(): LiveData<List<Feeling>>
}
