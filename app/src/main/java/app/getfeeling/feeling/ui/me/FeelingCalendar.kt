package app.getfeeling.feeling.ui.me

import app.getfeeling.feeling.room.entities.Feeling
import org.threeten.bp.Month
import org.threeten.bp.YearMonth

class FeelingCalendar {

    private var feelingMonths: MutableList<FeelingMonth> = mutableListOf()

    private val baseNoOfMonths = 2

    private var oldestFeelingMonth: YearMonth = YearMonth.now()

    init {
        feelingMonths.add(FeelingMonth(oldestFeelingMonth))

        for (i in 1..baseNoOfMonths) {
            addFeelingMonth()
        }
    }

    operator fun get(index: Int) = feelingMonths[index]

    fun insert(feeling: Feeling) {
        // make sure all months exist up till oldest
        while (oldestFeelingMonth.isAfter(feeling.getYearMonth())) {
            addFeelingMonth()
        }

        feelingMonths[feeling.getYearMonth().monthsBeforeNow()].insert(feeling)
    }

    fun numOfMonths() = feelingMonths.size

    private fun addFeelingMonth() {
        oldestFeelingMonth = oldestFeelingMonth.minusMonths(1)
        feelingMonths.add(FeelingMonth(oldestFeelingMonth))
    }

    private fun YearMonth.monthsBeforeNow(): Int {
        val now = YearMonth.now()
        return (now.year - this.year) * Month.values().size + (now.monthValue - this.monthValue)
    }
}
