package co.feelingapp.feeling.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import co.feelingapp.feeling.room.entities.User

@Dao
interface UserDao {
    @Insert
    fun insert(user: User)

    @Delete
    fun delete(user: User)

    @Query("SELECT * FROM users WHERE id = :id LIMIT 1")
    fun get(id: Int): User
}
