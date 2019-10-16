package app.getfeeling.feeling.repository.interfaces

import androidx.lifecycle.LiveData
import app.getfeeling.feeling.valueobjects.Feeling
import org.threeten.bp.YearMonth

interface IFeelingRepository {

    fun getFeeling(id: String): LiveData<Feeling>

    fun getFeelings(yearMonth: YearMonth): List<Feeling>

    suspend fun addFeeling(feeling: Feeling)
}
