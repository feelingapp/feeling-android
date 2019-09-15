package app.getfeeling.feeling.ui.me

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.getfeeling.feeling.repository.interfaces.IFeelingRepository
import app.getfeeling.feeling.room.entities.Feeling
import app.getfeeling.feeling.room.entities.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.threeten.bp.OffsetDateTime
import javax.inject.Inject

class MeViewModel @Inject constructor(private val repository: IFeelingRepository) : ViewModel() {

    private val allFeelings: LiveData<List<Feeling>> = repository.getAllFeelings()

    val feelingCalendar: LiveData<FeelingCalendar> =
        Transformations.map(allFeelings) { allFeelings ->
            FeelingCalendar().apply {
                allFeelings.forEach(::insert)
            }
        }

    // Test field
    private var date: OffsetDateTime = OffsetDateTime.now()

    fun getTitle(): String = "Welcome Back Michael"

    // Test function
    fun addFeeling() {
        viewModelScope.launch(Dispatchers.IO) {
            if (repository.getUser(1) == null) {
                repository.addUser(User(1, "Adam", "Cox", "idk@something.com"))
            }

            repository.addFeeling(
                Feeling(1, "Amazing", "dunno something", "#tag", date)
            )

            date = date.minusDays(1)
        }
    }
}
