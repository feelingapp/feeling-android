package app.getfeeling.feeling.ui.me

import app.getfeeling.feeling.room.entities.Feeling
import org.threeten.bp.Month
import org.threeten.bp.YearMonth

class FeelingCalendar {

    private var allFeelings: MutableList<FeelingMonth> = mutableListOf()

    val size = allFeelings.size

    private val baseNoOfMonths = 2

    private var oldestFeelingMonth: YearMonth = YearMonth.now()

    init {
        allFeelings.add(FeelingMonth(oldestFeelingMonth))

        for (i in 1..baseNoOfMonths) {
            addFeelingMonth()
        }
    }

    operator fun get(index: Int) = allFeelings[index]

    fun insert(feeling: Feeling) {
        // make sure all months exist up till oldest
        while (oldestFeelingMonth.isAfter(feeling.yearMonth)) {
            addFeelingMonth()
        }

        allFeelings[feeling.yearMonth.monthsBeforeNow()].insert(feeling)
    }

    private fun addFeelingMonth() {
        oldestFeelingMonth = oldestFeelingMonth.minusMonths(1)
        allFeelings.add(FeelingMonth(oldestFeelingMonth))
    }

    private fun YearMonth.monthsBeforeNow(): Int {
        val now = YearMonth.now()
        return (now.year - this.year) * Month.values().size + (now.monthValue - this.monthValue)
    }
}
