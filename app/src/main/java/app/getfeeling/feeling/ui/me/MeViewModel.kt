package app.getfeeling.feeling.ui.me

import androidx.lifecycle.*
import app.getfeeling.feeling.valueobjects.User
import app.getfeeling.feeling.repository.interfaces.IFeelingRepository
import app.getfeeling.feeling.repository.interfaces.ITokenRepository
import app.getfeeling.feeling.repository.interfaces.IUserRepository
import app.getfeeling.feeling.valueobjects.Feeling
import kotlinx.coroutines.launch
import javax.inject.Inject

class MeViewModel @Inject constructor(
    private val userRepository: IUserRepository,
    feelingRepository: IFeelingRepository
    tokenRepository: ITokenRepository,
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

    fun getUser() = viewModelScope.launch {
        userId?.apply {
            userRepository.getUser(userId).apply {
                _user.addSource(this.data) { _user.postValue(it) }
            }
        }
    }
}
