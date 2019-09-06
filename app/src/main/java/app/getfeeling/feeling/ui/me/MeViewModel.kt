package app.getfeeling.feeling.ui.me

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

    val allFeelings =
        listOf(
            listOf(
                Feeling(1, "Loving", "", "", OffsetDateTime.parse("2019-08-22T00:00:00+00:00")),
                Feeling(1, "Sad", "", "", OffsetDateTime.parse("2019-08-23T00:00:00+00:00"))
            ),
            listOf(
                Feeling(1, "Happy", "", "", OffsetDateTime.parse("2019-09-23T10:00:00+00:00")),
                Feeling(1, "Angry", "", "", OffsetDateTime.parse("2019-09-24T10:00:00+00:00"))
            ),
            listOf(
                Feeling(1, "Neutral", "", "", OffsetDateTime.parse("2019-10-23T10:00:00+00:00")),
                Feeling(1, "Loving", "", "", OffsetDateTime.parse("2019-10-24T10:00:00+00:00"))
            )
        )

    private var date: OffsetDateTime = OffsetDateTime.now()

    fun getTitle(): String = "Welcome Back Michael"

    fun getAllFeelingsGroupedByMonth() = repository.getAllFeelingsGroupedByMonth()

    fun addFeeling() {
        viewModelScope.launch(Dispatchers.IO) {
            if (repository.getUser(1) == null) {
                repository.addUser(User(1, "Adam", "Cox", "idk@something.com"))
            }

            repository.addFeeling(
                Feeling(1, "Happy", "dunno something", "#tag", date)
            )

            date = date.minusDays(1)
        }
    }
}
