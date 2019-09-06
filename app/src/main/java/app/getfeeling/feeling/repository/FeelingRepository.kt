package app.getfeeling.feeling.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import app.getfeeling.feeling.api.FeelingService
import app.getfeeling.feeling.api.models.ErrorsModel
import app.getfeeling.feeling.repository.interfaces.IFeelingRepository
import app.getfeeling.feeling.room.dao.FeelingDao
import app.getfeeling.feeling.room.dao.UserDao
import app.getfeeling.feeling.room.entities.Feeling
import app.getfeeling.feeling.room.entities.User
import app.getfeeling.feeling.ui.me.FeelingCalendar
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
    private val userDao: UserDao,
    private val feelingService: FeelingService,
    private val errorConverter: Converter<ResponseBody, ErrorsModel>
) : IFeelingRepository {

    override fun getFeelingCalendar(): LiveData<FeelingCalendar> =
        liveData(Dispatchers.IO) {
            val feelingCalendar = FeelingCalendar()

            val allFeelings = feelingDao.getAllFeelings().value!!
            allFeelings.forEach(feelingCalendar::insert)

            emit(feelingCalendar)
        }

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

    // Test function
    override suspend fun addUser(user: User) = withContext(Dispatchers.IO) {
        userDao.insert(user)
    }

    // Test function
    override fun getUser(id: Int): User? = userDao.get(id)
}

