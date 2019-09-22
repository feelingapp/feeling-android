package app.getfeeling.feeling.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import app.getfeeling.feeling.valueobjects.Quote

@Dao
interface QuoteDao {
    @Insert
    fun insert(quote: Quote)

    @Delete
    fun delete(quote: Quote)

    @Query("SELECT * FROM quotes WHERE emotion = :emotion LIMIT 1")
    fun get(emotion: String): Quote
}
