package app.getfeeling.feeling.repository.interfaces

import androidx.lifecycle.LiveData

interface IFeelingRepository {

    fun getStatus(): LiveData<Boolean>
}
