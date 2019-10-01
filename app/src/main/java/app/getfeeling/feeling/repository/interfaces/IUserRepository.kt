package app.getfeeling.feeling.repository.interfaces

import app.getfeeling.feeling.repository.helpers.LiveResource
import app.getfeeling.feeling.valueobjects.User

interface IUserRepository {
    suspend fun getUser(userId: String): LiveResource<User>
}
