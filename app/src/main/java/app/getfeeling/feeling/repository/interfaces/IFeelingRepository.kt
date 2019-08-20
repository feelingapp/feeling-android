package app.getfeeling.feeling.repository.interfaces

import androidx.lifecycle.LiveData
import app.getfeeling.feeling.api.models.GetTokenModel
import app.getfeeling.feeling.api.models.TokenModel

interface IFeelingRepository {

    fun getStatus(): LiveData<String>

    fun exchangeCodeForToken(getTokenModel: GetTokenModel): LiveData<TokenModel>
}

