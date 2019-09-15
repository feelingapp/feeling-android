package app.getfeeling.feeling.repository.interfaces

import androidx.lifecycle.LiveData
import app.getfeeling.feeling.room.entities.Feeling

interface IFeelingRepository {

    fun getStatus(): LiveData<String>

    fun getAllFeelings(): LiveData<List<Feeling>>

    suspend fun addFeeling(feeling: Feeling)
}

