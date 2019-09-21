package app.getfeeling.feeling.ui.me

import app.getfeeling.feeling.room.entities.Feeling
import org.threeten.bp.YearMonth

class FeelingMonth(private val yearMonth: YearMonth) {

    var feelings: Array<Feeling?> = arrayOfNulls(monthLength)

    fun getWithOffset(position: Int) = feelings[position - dayOffset]

    fun insert(feeling: Feeling) {
        feelings[feeling.createdAt.dayOfMonth - 1] = feeling
    }

    val dayOffset: Int get() = yearMonth.atDay(1).dayOfWeek.value - 1

    val monthValue: Int get() = yearMonth.monthValue

    val monthArrayValue: Int get() = yearMonth.month.value - 1

    val monthLength: Int get() = yearMonth.month.length(yearMonth.isLeapYear)
}
