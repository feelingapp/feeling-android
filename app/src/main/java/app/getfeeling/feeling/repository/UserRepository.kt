package app.getfeeling.feeling.repository

import app.getfeeling.feeling.api.FeelingService
import app.getfeeling.feeling.models.User
import app.getfeeling.feeling.repository.helpers.LiveResource
import app.getfeeling.feeling.repository.helpers.NetworkBoundResource
import app.getfeeling.feeling.repository.interfaces.IUserRepository
import app.getfeeling.feeling.room.dao.UserDao
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val feelingService: FeelingService,
    private val userDao: UserDao
) : IUserRepository {
    override suspend fun getUser(userId: String): LiveResource<User> {
        return object : NetworkBoundResource<User, User>() {

            override suspend fun saveCallResult(data: User) = userDao.insert(data)

            // TODO: Figure out when we want to fetch from database/network
            override fun shouldFetch(data: User?): Boolean = true

            override suspend fun makeApiCall(): User? = feelingService.getMe().body()

            override suspend fun loadFromDb(): User? = userDao.get(userId)

        }.asLiveResource()
    }
}
