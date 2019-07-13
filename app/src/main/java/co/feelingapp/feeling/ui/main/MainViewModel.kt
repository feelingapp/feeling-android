package co.feelingapp.feeling.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import co.feelingapp.feeling.repo.interfaces.IFeelingRepository
import co.feelingapp.feeling.room.entities.Feeling
import javax.inject.Inject

class MainViewModel : ViewModel() {

    @Inject
    lateinit var repository: IFeelingRepository

    val feelings: LiveData<List<Feeling>>

    init {
        feelings = repository.feelings
    }
}
