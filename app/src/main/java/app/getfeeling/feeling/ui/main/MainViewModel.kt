package app.getfeeling.feeling.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import app.getfeeling.feeling.repository.interfaces.IFeelingRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: IFeelingRepository) : ViewModel() {

    lateinit var isApiOnline: LiveData<Boolean>

    fun updateStatus() {
        isApiOnline = repository.getStatus()
    }
}
