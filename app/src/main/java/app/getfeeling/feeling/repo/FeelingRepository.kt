package app.getfeeling.feeling.repo

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import app.getfeeling.feeling.repo.interfaces.IFeelingRepository
import app.getfeeling.feeling.room.dao.FeelingDao
import app.getfeeling.feeling.room.entities.Feeling
import app.getfeeling.feeling.web.FeelingAPI
import java.util.concurrent.Executor
import javax.inject.Inject

@WorkerThread
class FeelingRepository : IFeelingRepository {
    override val feelings: LiveData<List<Feeling>>
        get() = feelingsByDate()

    @Inject
    lateinit var feelingDao: FeelingDao

    @Inject
    lateinit var feelingAPI: FeelingAPI

    @Inject
    lateinit var executor: Executor

    override fun insert(feeling: Feeling) {
//        var user : Deferred<Response<User>>
//        executor.execute { user = feelingAPI.getUserAsync(feeling.id) }

        feelingDao.insert(feeling)
    }

    override fun delete(feeling: Feeling) {
        feelingDao.delete(feeling)
    }

    override fun feelingsByDate(): LiveData<List<Feeling>> {
        return feelingDao.feelingsByDate()
    }
}