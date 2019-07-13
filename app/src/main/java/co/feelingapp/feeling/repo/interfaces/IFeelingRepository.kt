package co.feelingapp.feeling.repo.interfaces

import androidx.lifecycle.LiveData
import co.feelingapp.feeling.room.entities.Feeling

interface IFeelingRepository {

    val feelings: LiveData<List<Feeling>>

    fun insert(feeling: Feeling)

    fun delete(feeling: Feeling)

    fun feelingsByDate(): LiveData<List<Feeling>>
}
