package app.getfeeling.feeling.repository.interfaces

import androidx.lifecycle.LiveData
import app.getfeeling.feeling.api.models.GetTokenModel
import app.getfeeling.feeling.api.models.TokenModel
import app.getfeeling.feeling.room.entities.Feeling
import org.threeten.bp.OffsetDateTime

interface IFeelingRepository {

    fun getStatus(): LiveData<String>

    fun getFeelingsByMonth(
        monthStart: OffsetDateTime,
        monthEnd: OffsetDateTime
    ): LiveData<List<Feeling>>
}

