package app.getfeeling.feeling.repository.interfaces

import androidx.lifecycle.LiveData
import app.getfeeling.feeling.room.entities.Feeling

interface IFeelingRepository {

    val feelings: LiveData<List<Feeling>>

    fun insert(feeling: Feeling)

    fun delete(feeling: Feeling)

    fun feelingsByDate(): LiveData<List<Feeling>>
}
