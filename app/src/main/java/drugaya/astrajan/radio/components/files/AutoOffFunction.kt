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
import drugaya.astrajan.radio.rossiya_app.util.App


object AutoOffFunction {
    fun setAutoOffFunctionality(root: View, context: Context) {
        val rangeSlider = root.range_slider
        val autoOffTextview = root.auto_off_textview

        rangeSlider.addOnSliderTouchListener(object : RangeSlider.OnSliderTouchListener{ override fun onStopTrackingTouch(slider: RangeSlider) {}
            @SuppressLint("SetTextI18n", "UnspecifiedImmutableFlag")
            override fun onStartTrackingTouch(slider: RangeSlider) {
                // recojmos valor de slider y enseñamos
                val valueRangeSlider = rangeSlider.values[0]
                autoOffTextview.text = valueRangeSlider.toString()
                val minInt = valueRangeSlider.toInt()
                Toast.makeText( context, minInt.toString() + " " + context.getString(R.string.minute), Toast.LENGTH_SHORT).show()
                autoOffTextview.text = " " + minInt.toString() + " " + context.getString(R.string.minute)


             // instalamos alarma con el valor de el slider
             val am = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
             val i = Intent(context, OffRadioReceiver::class.java); i.putExtra("info", "off_radio")
             val pi = PendingIntent.getBroadcast(context, 1, i, PendingIntent.FLAG_UPDATE_CURRENT)

             if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){ // tiempo en minutos, minInt = 5
                 am.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (60000 * minInt ).toLong(), pi)
             } else { // tiempo en minutos
                 am.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (60000 * minInt ).toLong(), pi)
             }
            }
        })

    }
}