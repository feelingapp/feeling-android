package app.getfeeling.feeling.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import app.getfeeling.feeling.repository.interfaces.IFeelingRepository
import app.getfeeling.feeling.room.dao.FeelingDao
import app.getfeeling.feeling.room.entities.Feeling
import app.getfeeling.feeling.api.FeelingService
import java.util.concurrent.Executor
import javax.inject.Inject

@WorkerThread
class FeelingRepository : IFeelingRepository {
    override val feelings: LiveData<List<Feeling>>
        get() = feelingsByDate()

    @Inject
    lateinit var feelingDao: FeelingDao

    @Inject
    lateinit var feelingService: FeelingService

    @Inject
    lateinit var executor: Executor

    override fun insert(feeling: Feeling) {
        feelingDao.insert(feeling)
    }

    override fun delete(feeling: Feeling) {
        feelingDao.delete(feeling)
    }

    override fun feelingsByDate(): LiveData<List<Feeling>> {
        return feelingDao.feelingsByDate()
    }
}
