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
    fun get(id: String): LiveData<Feeling>

    @Query("SELECT * FROM feelings WHERE strftime('%m', createdAt) = :month AND strftime('%Y', createdAt) = :year")
    fun get(month: String, year: String): List<Feeling>

    @Query("SELECT * FROM feelings")
    fun getAll(): LiveData<List<Feeling>>

    @Insert
    suspend fun insert(feeling: Feeling)

    @Delete
    suspend fun delete(feeling: Feeling)
}
