package app.getfeeling.feeling.ui.me

import androidx.paging.DataSource
import app.getfeeling.feeling.repository.FeelingRepository
import org.threeten.bp.YearMonth


class FeelingMonthDataSourceFactory(private val feelingRepository: FeelingRepository) :
    DataSource.Factory<YearMonth, FeelingMonth>() {

    override fun create(): DataSource<YearMonth, FeelingMonth> {
        return FeelingMonthDataSource(feelingRepository)
    }
}
