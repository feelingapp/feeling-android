package app.getfeeling.feeling.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import app.getfeeling.feeling.api.FeelingService
import app.getfeeling.feeling.valueobjects.Errors
import app.getfeeling.feeling.repository.interfaces.IFeelingRepository
import app.getfeeling.feeling.room.dao.FeelingDao
import app.getfeeling.feeling.valueobjects.Feeling
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Converter
import javax.inject.Inject
import javax.inject.Singleton

@WorkerThread
@Singleton
class FeelingRepository @Inject constructor(
    private val feelingDao: FeelingDao,
    private val feelingService: FeelingService,
    private val errorConverter: Converter<ResponseBody, Errors>
) : IFeelingRepository {

    override fun getAllFeelings(): LiveData<List<Feeling>> = feelingDao.getAllFeelings()

    override fun getFeeling(id: String): LiveData<Feeling> = feelingDao.get(id)

    override fun getStatus() = liveData(Dispatchers.IO) {
        try {
            val response = feelingService.getStatus()
            if (response.isSuccessful) {
                emit("Online")
            } else {
                emit("Offline")
            }
        } catch (e: Throwable) {
            emit("Offline")
        }
    }

    override suspend fun addFeeling(feeling: Feeling) = withContext(Dispatchers.IO) {
        feelingDao.insert(feeling)
    }
}

