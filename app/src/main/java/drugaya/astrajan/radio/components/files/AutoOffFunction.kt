package drugaya.astrajan.radio.components.files
import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.View
import android.widget.Toast
import com.google.android.material.slider.RangeSlider
import drugaya.astrajan.radio.R
import kotlinx.android.synthetic.main.fragment_notifications.view.*

import drugaya.astrajan.radio.assets.OffRadioReceiver


object AutoOffFunction {
    fun setAutoOffFunctionality(root: View, context: Context) {
        val rangeSlider = root.range_slider
        val autoOffTextview = root.auto_off_textview

        rangeSlider.addOnSliderTouchListener(object : RangeSlider.OnSliderTouchListener{ override fun onStopTrackingTouch(slider: RangeSlider) {}
            @SuppressLint("SetTextI18n", "UnspecifiedImmutableFlag")
            override fun onStartTrackingTouch(slider: RangeSlider) {
                val valueRangeSlider = rangeSlider.values[0]
                autoOffTextview.text = valueRangeSlider.toString()
                val minInt = valueRangeSlider.toInt()
                Toast.makeText( context, minInt.toString() + " " + context.getString(R.string.minute), Toast.LENGTH_SHORT).show()
                autoOffTextview.text = " " + minInt.toString() + " " + context.getString(R.string.minute)



             val am = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
             val i = Intent(context, OffRadioReceiver::class.java)
             val pi = PendingIntent.getBroadcast(context, 1, i, PendingIntent.FLAG_UPDATE_CURRENT)
             if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){ // tiempo en minutos, minInt = 5
                 am.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (60000 * minInt ).toLong(), pi)
             } else { // tiempo en minutos
                 am.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (60000 * minInt ).toLong(), pi)
             }

               // off alarm
               // val intent = Intent(context, OffRadioReceiver::class.java)
               // val sender = PendingIntent.getBroadcast(context, 1, intent, PendingIntent.FLAG_CANCEL_CURRENT)
               // val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
               // alarmManager.cancel(sender)
            }
        })

    }
}