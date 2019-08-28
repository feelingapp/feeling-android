package app.getfeeling.feeling.ui.me

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.getfeeling.feeling.repository.interfaces.IFeelingRepository
import app.getfeeling.feeling.room.entities.Feeling
import org.threeten.bp.OffsetDateTime
import javax.inject.Inject

class MeViewModel @Inject constructor(private val repository: IFeelingRepository) : ViewModel() {
    private lateinit var _feelings: MutableLiveData<List<Feeling>>

    val feelings: LiveData<List<Feeling>>
        get() = _feelings

    fun getTitle(): String = "Welcome Back Michael"

    fun getFeelingsByMonth(monthStart: OffsetDateTime, monthEnd: OffsetDateTime) {
        _feelings = repository.getFeelingsByMonth(
            monthStart,
            monthEnd
        ) as MutableLiveData<List<Feeling>>
    }
    