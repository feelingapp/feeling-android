package app.getfeeling.feeling.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import app.getfeeling.feeling.valueobjects.Feeling

@Dao
interface FeelingDao {

    @Query("SELECT * FROM feelings WHERE id = :id")
    fun get(id: Int): LiveData<Feeling>

    @Insert
    suspend fun insert(feeling: Feeling)

    @Delete
    suspend fun delete(feeling: Feeling)

    @Query("SELECT * FROM feelings")
    fun getAllFeelings(): LiveData<List<Feeling>>
}
