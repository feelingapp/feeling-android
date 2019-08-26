package app.getfeeling.feeling.ui.me

import androidx.lifecycle.ViewModel
import app.getfeeling.feeling.repository.interfaces.IFeelingRepository
import javax.inject.Inject

class MeViewModel @Inject constructor(private val repository: IFeelingRepository) : ViewModel() {
    fun getTitle(): String = "Welcome Back Michael"
}
