package app.getfeeling.feeling.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import app.getfeeling.feeling.repository.interfaces.IFeelingRepository
import app.getfeeling.feeling.room.dao.FeelingDao
import app.getfeeling.feeling.valueobjects.Feeling
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.threeten.bp.YearMonth
import javax.inject.Inject
import javax.inject.Singleton

@WorkerThread
@Singleton
class FeelingRepository @Inject constructor(private val feelingDao: FeelingDao) :
    IFeelingRepository {

    override fun getFeeling(id: String): LiveData<Feeling> = feelingDao.get(id)

    override fun getFeelings(yearMonth: YearMonth): List<Feeling> =
        feelingDao.get(
            ((yearMonth.monthValue).toString().padStart(2, '0')),
            yearMonth.year.toString()
        )

    override fun getAllFeelings(): LiveData<List<Feeling>> = feelingDao.getAll()

    override suspend fun addFeeling(feeling: Feeling) = withContext(Dispatchers.IO) {
        feelingDao.insert(feeling)
    }
}
