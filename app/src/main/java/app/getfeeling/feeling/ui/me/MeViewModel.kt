package app.getfeeling.feeling.ui.me

import androidx.lifecycle.*
import app.getfeeling.feeling.models.User
import app.getfeeling.feeling.repository.interfaces.IFeelingRepository
import app.getfeeling.feeling.repository.interfaces.ITokenRepository
import app.getfeeling.feeling.repository.interfaces.IUserRepository
import app.getfeeling.feeling.room.entities.Feeling
import app.getfeeling.feeling.room.entities.User
import app.getfeeling.feeling.util.Emotion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MeViewModel @Inject constructor(
    private val userRepository: IUserRepository,
    private val tokenRepository: ITokenRepository,
    feelingRepository: IFeelingRepository
) : ViewModel() {
    private val userId = tokenRepository.getUserId()

    private val _user = MediatorLiveData<User?>()
    val user: LiveData<User?> = _user

    private val allFeelings: LiveData<List<Feeling>> = feelingRepository.getAllFeelings()

    val feelingCalendar: LiveData<FeelingCalendar> =
        Transformations.map(allFeelings) { allFeelings ->
            FeelingCalendar().apply {
                allFeelings.forEach(::insert)
            }
        }

    fun getUser() {
        viewModelScope.launch {
            userId?.apply {
                userRepository.getUser(userId).apply {
                    _user.addSource(this.data) { _user.postValue(it) }
                }
            }
        }
    }
}
