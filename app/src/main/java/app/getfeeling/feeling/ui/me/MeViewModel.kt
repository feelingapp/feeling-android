package app.getfeeling.feeling.ui.me

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import app.getfeeling.feeling.repository.interfaces.IFeelingRepository
import app.getfeeling.feeling.room.entities.Feeling
import javax.inject.Inject

class MeViewModel @Inject constructor(private val repository: IFeelingRepository) : ViewModel() {

    private val allFeelings: LiveData<List<Feeling>> = repository.getAllFeelings()

    val feelingCalendar: LiveData<FeelingCalendar> =
        Transformations.map(allFeelings) { allFeelings ->
            FeelingCalendar().apply {
                allFeelings.forEach(::insert)
            }
        }

    fun getTitle(): String = "Welcome Back Michael"
}
