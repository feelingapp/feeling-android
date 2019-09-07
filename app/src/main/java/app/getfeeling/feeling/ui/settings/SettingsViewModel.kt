package app.getfeeling.feeling.ui.settings

import androidx.lifecycle.ViewModel
import app.getfeeling.feeling.repository.interfaces.ITokenRepository
import javax.inject.Inject

class SettingsViewModel @Inject constructor(private val repository: ITokenRepository) :
    ViewModel() {

    fun signOut() {
        repository.clearToken()
    }
}
