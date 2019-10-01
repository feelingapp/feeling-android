package app.getfeeling.feeling.ui.me

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import app.getfeeling.feeling.repository.FeelingRepository
import org.threeten.bp.YearMonth


class FeelingMonthDataSourceFactory(private val feelingRepository: FeelingRepository) :
    DataSource.Factory<YearMonth, FeelingMonth>() {

    val sourceLiveData = MutableLiveData<FeelingMonthDataSource>()

    override fun create(): DataSource<YearMonth, FeelingMonth> {
        val latestSource = FeelingMonthDataSource(feelingRepository)
        sourceLiveData.postValue(latestSource)
        return latestSource
    }
}
