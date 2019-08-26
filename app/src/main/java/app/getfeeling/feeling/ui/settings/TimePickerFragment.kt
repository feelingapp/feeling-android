package app.getfeeling.feeling.ui.settings

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.TimePicker
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import org.threeten.bp.OffsetDateTime

class TimePickerFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val time = OffsetDateTime.now()
        val hour = time.hour
        val minute = time.minute

        return TimePickerDialog(activity, this, hour, minute, true)
    }

    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
        Toast.makeText(context, "$hourOfDay:$minute", Toast.LENGTH_LONG).show()
    }
}