package app.getfeeling.feeling.repository.helpers

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Job

data class LiveResource<T>(
    val data: LiveData<T?>,
    val status: LiveData<Status>,
    val error: LiveData<Exception?>,
    val job: Job? = null
)

enum class Status {
    SUCCESS, FAILED, LOADING
}
