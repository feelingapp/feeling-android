package app.getfeeling.feeling.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import app.getfeeling.feeling.repository.interfaces.IFeelingRepository
import app.getfeeling.feeling.room.entities.Feeling
import javax.inject.Inject

class MainViewModel : ViewModel() {

    @Inject
    lateinit var repository: IFeelingRepository

    val feelings: LiveData<List<Feeling>>

    init {
        feelings = repository.feelings
    }
}
