package app.getfeeling.feeling.repository.helpers

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

abstract class NetworkBoundResource<ResultType, RequestType> {

    private val result = MutableLiveData<ResultType?>()

    private val status = MutableLiveData<Status>()

    private val error = MutableLiveData<Exception?>()

    private val job = Job()

    init {
        val context = Dispatchers.IO
        context + job

        CoroutineScope(context).launch {
            status.postValue(Status.LOADING)
            val dbSource = loadFromDb()
            if (shouldFetch(dbSource)) {
                fetchFromNetwork(dbSource)
            } else {
                status.postValue(Status.SUCCESS)
                result.postValue(dbSource)
            }
        }
    }

    private suspend fun fetchFromNetwork(dbSource: ResultType?) {
        assert(status.value == Status.LOADING)
        dbSource?.let(result::postValue)

        try {
            val apiResponse = makeApiCall()

            apiResponse?.let {
                saveCallResult(it)
                result.postValue(loadFromDb())
            } ?: run {
                if (dbSource == null)
                    result.postValue(null)
            }

            status.postValue(Status.SUCCESS)

        } catch (e: Exception) {
            assert(result.value == dbSource)
            status.postValue(Status.FAILED)
            error.postValue(e)
        }
    }

    protected abstract suspend fun loadFromDb(): ResultType?

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract suspend fun makeApiCall(): RequestType?

    protected abstract suspend fun saveCallResult(data: RequestType)

    fun asLiveResource() = LiveResource(result, status, error, job)

}
