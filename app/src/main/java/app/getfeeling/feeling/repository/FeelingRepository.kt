package app.getfeeling.feeling.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.liveData
import app.getfeeling.feeling.api.FeelingService
import app.getfeeling.feeling.api.models.ErrorsModel
import app.getfeeling.feeling.repository.interfaces.IFeelingRepository
import app.getfeeling.feeling.room.dao.FeelingDao
import kotlinx.coroutines.Dispatchers
import okhttp3.ResponseBody
import retrofit2.Converter
import javax.inject.Inject


@WorkerThread
class FeelingRepository @Inject constructor(
    private val feelingDao: FeelingDao,
    private val feelingService: FeelingService,
    private val errorConverter: Converter<ResponseBody, ErrorsModel>
) : IFeelingRepository {

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
}
