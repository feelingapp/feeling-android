package app.getfeeling.feeling.repository.interfaces

import androidx.lifecycle.LiveData
import app.getfeeling.feeling.valueobjects.Feeling
import org.threeten.bp.YearMonth

interface IFeelingRepository {

    fun getFeelings(yearMonth: YearMonth): List<Feeling>

    fun getFeeling(id: String): LiveData<Feeling>

    suspend fun addFeeling(feeling: Feeling)
}

