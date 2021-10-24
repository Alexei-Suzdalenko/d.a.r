package drugaya.astrajan.radio.components.files
import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_notifications.view.*
import com.google.android.material.datepicker.MaterialDatePicker.Builder.datePicker
import drugaya.astrajan.radio.assets.OffRadioReceiver
import java.util.*
object SetAlarm {
    @SuppressLint("UnspecifiedImmutableFlag", "SetTextI18n")
    fun setAlarmFunction(view: View, context: Context ){
        val timePicker = view.time_picker
        val timeClockTextView = view.time_clock

        timePicker.setOnTimeChangedListener { _, hourOfDay, minute ->
            var hoursText = "0"
            if(hourOfDay < 10){ hoursText += hourOfDay.toString()
            } else hoursText = hourOfDay.toString()
            var minuteText = "0"
            if(minute < 10){ minuteText += minute.toString()
            } else minuteText = minute.toString()
            timeClockTextView.text = "$hoursText  :  $minuteText"

            val cal: Calendar = Calendar.getInstance()
            if (Build.VERSION.SDK_INT >= 23 ){
                cal.set(Calendar.HOUR, timePicker.hour)
                cal.set(Calendar.MINUTE, timePicker.minute)
            } else{
                cal.set(Calendar.HOUR, timePicker.currentHour)
                cal.set(Calendar.MINUTE, timePicker.currentMinute)
            }
            cal.set(Calendar.SECOND, 0)
            cal.set(Calendar.MILLISECOND, 0)


            var millisTime: Long = cal.timeInMillis
            if( millisTime < System.currentTimeMillis() ) millisTime += 86400000

            // instalamos alarma con el valor de el slider
            val am = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val i = Intent(context, OffRadioReceiver::class.java); i.putExtra("set_alarm", "set_alarm")
            val pi = PendingIntent.getBroadcast(context, 11, i, PendingIntent.FLAG_UPDATE_CURRENT)

            if ( Build.VERSION.SDK_INT>= Build.VERSION_CODES.M ) { am.setExactAndAllowWhileIdle( AlarmManager.RTC_WAKEUP, millisTime, pi )
            } else { am.setExact(AlarmManager.RTC_WAKEUP, millisTime, pi) }
        }



    }
}