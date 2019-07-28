package app.getfeeling.feeling.repository

import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.liveData
import app.getfeeling.feeling.api.FeelingService
import app.getfeeling.feeling.repository.interfaces.IFeelingRepository
import app.getfeeling.feeling.room.dao.FeelingDao
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException
import javax.inject.Inject

@WorkerThread
class FeelingRepository @Inject constructor(
    private val feelingDao: FeelingDao,
    private val feelingService: FeelingService
) : IFeelingRepository {

    override fun getStatus() = liveData(Dispatchers.IO) {
        try {
            val response = feelingService.getStatus()
            if (response.isSuccessful) {
                emit(response.body()!!.status)
            } else {
                emit("Offline")
            }
        } catch (e: HttpException) {
            Log.e("REQUEST", "Exception ${e.message}")
            emit("Offline")
        } catch (e: Throwable) {
            Log.e("REQUEST", "Oops: Something else went wrong")
            emit("Offline")
        }
    }
}
