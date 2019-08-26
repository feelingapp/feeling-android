package app.getfeeling.feeling.ui.me

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import app.getfeeling.feeling.repository.interfaces.IFeelingRepository
import javax.inject.Inject

class MeViewModel @Inject constructor(private val repository: IFeelingRepository) : ViewModel() {

    private val input = MutableLiveData<Boolean>()
    var isApiOnline: LiveData<String> = Transformations.switchMap(input) {
        repository.getStatus()
    }

    fun setInput() {
        input.value = input.value?.not()
    }
}
