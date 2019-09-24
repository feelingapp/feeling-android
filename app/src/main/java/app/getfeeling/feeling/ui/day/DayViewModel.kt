package app.getfeeling.feeling.ui.day

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import app.getfeeling.feeling.R
import app.getfeeling.feeling.repository.interfaces.IFeelingRepository
import app.getfeeling.feeling.valueobjects.Feeling
import java.util.*
import javax.inject.Inject

class DayViewModel @Inject constructor(context: Context, repository: IFeelingRepository) :
    ViewModel() {

    private val feelingId: MutableLiveData<String> = MutableLiveData()

    val feeling: LiveData<Feeling> = Transformations.switchMap(feelingId) { feelingId ->
        repository.getFeeling(feelingId)
    }

    val title: LiveData<String> = Transformations.map(feeling) { feeling ->
        with(feeling.createdAt) {
            "${ordinalNumbers[dayOfMonth - 1]} ${months[monthValue - 1]} $year"
        }
    }

    val emotion: LiveData<String> = Transformations.map(feeling) { feeling ->
        context.getString(feeling.emotion.getString()).toLowerCase(Locale.getDefault())
    }

    fun setFeelingId(id: String) {
        feelingId.value = id
    }

    private val ordinalNumbers: Array<String> =
        context.resources.getStringArray(R.array.ordinal_numbers)

    private val months: Array<String> =
        context.resources.getStringArray(R.array.months)
}
