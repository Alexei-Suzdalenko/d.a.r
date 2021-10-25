package drugaya.astrajan.radio.components.files
import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.widget.SwitchCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.fragment_notifications.view.*
import com.google.android.material.datepicker.MaterialDatePicker.Builder.datePicker
import drugaya.astrajan.radio.assets.OffRadioReceiver
import drugaya.astrajan.radio.rossiya_app.util.App.Companion.editor
import drugaya.astrajan.radio.rossiya_app.util.App.Companion.sharedPreferences
import drugaya.astrajan.radio.rossiya_app.util.ServiceRadio
import drugaya.astrajan.radio.rossiya_app.util.WaitAlarmService
import java.util.*
class SetAlarm (val view: View, val context: Context) {
    val timeClockTextView: TextView = view.time_clock
    val switchCompatAlarm: SwitchCompat = view.alarm_swith

    @SuppressLint("UnspecifiedImmutableFlag", "SetTextI18n")
    // mostramos swither en posicion enbled y enseñamos la hora de la alarma si esta instalada
    fun setAlarmFunction(){
        view.numberPicker_hours.minValue = 0
        view.numberPicker_hours.maxValue = 23
        view.numberPicker_hours.setFormatter{String.format("%02d", it)}
        view.numberPicker_minute.minValue = 0
        view.numberPicker_minute.maxValue = 59
        view.numberPicker_minute.setFormatter{String.format("%02d", it)}

       // timePicker.setIs24HourView(true)
        if ( "enabled" == sharedPreferences.getString("alarm", "none").toString() ){
            switchCompatAlarm.text = "On"
            switchCompatAlarm.isChecked = true
            timeClockTextView.text = sharedPreferences.getString("current_alarm", "").toString()

            val setHour = sharedPreferences.getInt("alarm_hours", 111)
            val setMin  = sharedPreferences.getInt("alarm_minute", 111)
            if(setHour != 111){ view.numberPicker_minute.value = setMin; view.numberPicker_hours.value  = setHour }
        }

        // click button set alarm
        view.set_alarm_button.setOnClickListener { interSetAlarm() }

        // al hacer click en el switch compat o instalamos la alarma o desabilitamos la alarma instalada
      switchCompatAlarm.setOnCheckedChangeListener { _, isChecked ->
          if (isChecked) { interSetAlarm(); Toast.makeText(context, "ON ALARM", Toast.LENGTH_SHORT).show()
           } else {
              switchCompatAlarm.text = "Off"
              editor.putString("alarm", "disabled"); editor.apply(); Toast.makeText(context, "DISABLED ALARM", Toast.LENGTH_SHORT).show()
               // off alarm
               val intent = Intent(context, OffRadioReceiver::class.java)
               val sender = PendingIntent.getBroadcast(context, 11, intent, PendingIntent.FLAG_CANCEL_CURRENT)
               val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
               alarmManager.cancel(sender)
           }
       }
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    private fun interSetAlarm(){
        try { context.stopService(Intent(context, WaitAlarmService::class.java)) } catch (e: Exception){}
        val hours    = view.numberPicker_hours.value
        val minute   = view.numberPicker_minute.value
        val calendar = Calendar.getInstance()
        calendar[Calendar.HOUR_OF_DAY] = hours
        calendar[Calendar.MINUTE]            = minute
        calendar[Calendar.SECOND]           = 0
        calendar[Calendar.MILLISECOND]   = 0
        var milliseconds = calendar.timeInMillis
        if( milliseconds < System.currentTimeMillis() ) milliseconds += 86400000

        val intent = Intent(context, OffRadioReceiver::class.java); intent.putExtra("set_alarm", "set_alarm")
        val pendingIntent = PendingIntent.getBroadcast(context, 2, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        if ( Build.VERSION.SDK_INT  >= Build.VERSION_CODES.M ) { alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, milliseconds, pendingIntent)
        } else { alarmManager.setExact(AlarmManager.RTC_WAKEUP, milliseconds, pendingIntent) }

        // guardamos string alarm data
        var hoursText = "0"; var minuteText = "0"
        if(hours < 10){ hoursText += hours.toString()
        } else hoursText = hours.toString()
        if(minute < 10){ minuteText += minute.toString()
        } else minuteText = minute.toString()

        editor.putString("current_alarm", "$hoursText : $minuteText")
        editor.putString("alarm", "enabled")
        editor.putInt( "alarm_hours", hours )
        editor.putInt( "alarm_minute", minute )
        editor.putLong("alarm_time", milliseconds)
        editor.apply()
        // seteamos switcher en ON y encendido
        switchCompatAlarm.text = "On"
        switchCompatAlarm.isChecked = true

        Toast.makeText(context,  "$hoursText : $minuteText", Toast.LENGTH_SHORT).show()
        timeClockTextView.text = sharedPreferences.getString("current_alarm", "").toString()
        try { ContextCompat.startForegroundService(context, Intent(context, WaitAlarmService::class.java)) } catch (e: Exception) {}
    }


}