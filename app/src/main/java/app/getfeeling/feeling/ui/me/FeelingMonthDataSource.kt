package app.getfeeling.feeling.ui.me

import androidx.paging.PageKeyedDataSource
import app.getfeeling.feeling.repository.FeelingRepository
import org.threeten.bp.YearMonth

class FeelingMonthDataSource(private val feelingRepository: FeelingRepository) :
    PageKeyedDataSource<YearMonth, FeelingMonth>() {

    override fun loadInitial(
        params: LoadInitialParams<YearMonth>,
        callback: LoadInitialCallback<YearMonth, FeelingMonth>
    ) {
        val (feelingMonths, previousYearMonth) = constructFeelingMonth(
            YearMonth.now(),
            params.requestedLoadSize
        )
        callback.onResult(feelingMonths, null, previousYearMonth)
    }

    override fun loadAfter(
        params: LoadParams<YearMonth>,
        callback: LoadCallback<YearMonth, FeelingMonth>
    ) {
        val (feelingMonths, previousYearMonth) = constructFeelingMonth(
            params.key,
            params.requestedLoadSize
        )
        callback.onResult(feelingMonths, previousYearMonth)
    }

    override fun loadBefore(
        params: LoadParams<YearMonth>,
        callback: LoadCallback<YearMonth, FeelingMonth>
    ) = throw NotImplementedError()

    private fun constructFeelingMonth(
        yearMonth: YearMonth,
        requestedLoadSize: Int
    ): Pair<List<FeelingMonth>, YearMonth> {
        val feelingMonths: MutableList<FeelingMonth> = mutableListOf()
        @Suppress("NAME_SHADOWING")
        var yearMonth = yearMonth

        for (i in 0..requestedLoadSize) {
            feelingMonths.add(FeelingMonth(yearMonth).apply {
                feelingRepository.getFeelings(yearMonth).forEach(::insert)
            })
            yearMonth = yearMonth.minusMonths(1)
        }

        return Pair(feelingMonths, yearMonth)
    }
}
