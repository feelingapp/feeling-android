package app.getfeeling.feeling.ui.me

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import app.getfeeling.feeling.repository.interfaces.ITokenRepository
import app.getfeeling.feeling.repository.interfaces.IUserRepository
import app.getfeeling.feeling.valueobjects.User
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.threeten.bp.YearMonth
import javax.inject.Inject

class MeViewModel @Inject constructor(
    private val userRepository: IUserRepository,
    tokenRepository: ITokenRepository,
    feelingMonthDataSourceFactory: DataSource.Factory<YearMonth, FeelingMonth>,
    pagedListConfig: PagedList.Config
) : ViewModel() {

    private val userId: String? = tokenRepository.getUserId()

    private val _user: MediatorLiveData<User?> = MediatorLiveData()
    val user: LiveData<User?> = _user

    val feelingMonths: LiveData<PagedList<FeelingMonth>> =
        LivePagedListBuilder(feelingMonthDataSourceFactory, pagedListConfig).build()

    fun getUser(): Job = viewModelScope.launch {
        userId?.let {
            userRepository.getUser(userId).apply {
                _user.addSource(data) { _user.postValue(it) }
            }
        }
    }
}
