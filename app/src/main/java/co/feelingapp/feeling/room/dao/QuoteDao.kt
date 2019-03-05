package co.feelingapp.feeling.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import co.feelingapp.feeling.room.entities.Quote

@Dao
interface QuoteDao {
    @Insert
    fun insert(quote: Quote)

    @Delete
    fun delete(quote: Quote)

    @Query("SELECT * FROM quotes WHERE emotion = :emotion LIMIT 1")
    fun getQuoteByEmotion(emotion: String): Quote
}
