package app.getfeeling.feeling.repository.interfaces

import androidx.lifecycle.LiveData
import app.getfeeling.feeling.room.entities.Feeling
import app.getfeeling.feeling.room.entities.User

interface IFeelingRepository {

    fun getStatus(): LiveData<String>

    fun getAllFeelings(): LiveData<List<Feeling>>

    suspend fun addFeeling(feeling: Feeling)

    // Test function
    suspend fun addUser(user: User)

    // Test function
    fun getUser(id: Int): User?
}

