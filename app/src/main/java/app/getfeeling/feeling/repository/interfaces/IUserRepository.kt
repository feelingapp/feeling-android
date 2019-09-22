package app.getfeeling.feeling.repository.interfaces

import app.getfeeling.feeling.valueobjects.User
import app.getfeeling.feeling.repository.helpers.LiveResource

interface IUserRepository {
    suspend fun getUser(userId: String): LiveResource<User>
}
