package drugaya.astrajan.radio.assets
import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

import android.os.PowerManager
import android.os.PowerManager.WakeLock
import android.app.AlarmManager

import android.app.PendingIntent
import android.media.RingtoneManager
import android.net.Uri
import android.util.Log
import androidx.core.app.JobIntentService.enqueueWork
import androidx.core.content.ContextCompat
import androidx.legacy.content.WakefulBroadcastReceiver
import drugaya.astrajan.radio.R
import drugaya.astrajan.radio.rossiya_app.util.App
import drugaya.astrajan.radio.rossiya_app.util.App.Companion.stopRadio
import drugaya.astrajan.radio.rossiya_app.util.ServiceRadio
import java.lang.Exception

class OffRadioReceiver : BroadcastReceiver () {
    lateinit var pm: PowerManager
    lateinit var wl: WakeLock

    @SuppressLint("InvalidWakeLockTag")
    override fun onReceive(context: Context?, intent: Intent?) {
       try {
           pm = context!!.getSystemService(Context.POWER_SERVICE) as PowerManager
           wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "kio")
           wl.acquire(2*60*1000L )
           val wakeIntent = Intent()
           wakeIntent.setClassName("drugaya.astrajan.radio", "drugaya.astrajan.radio.MainActivity")
           wakeIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
           context.startActivity(wakeIntent)

           val notification: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
           val r = RingtoneManager.getRingtone(context, notification)
           r.play()

           wl.release()
       } catch (e: Exception) { Toast.makeText(context, context!!.resources.getString(R.string.error_app), Toast.LENGTH_SHORT).show() }


        // si la radio esta en funcionameto  intent info es "off_radio" la apagamos
        if (intent?.getStringExtra("info") == "off_radio"){
            Toast.makeText(context, "RADIO OFF", Toast.LENGTH_LONG).show(); context!!.stopService( Intent( context, ServiceRadio::class.java ))
        }

        if( intent?.getStringExtra("set_alarm") == "set_alarm"){
            Toast.makeText(context, "START RADIO", Toast.LENGTH_LONG).show(); ContextCompat.startForegroundService( context!!, Intent( context, ServiceRadio::class.java ))
        }
        
    }

    // por ahora no se usa
  // fun setAlarm(context: Context) {
  //     val am = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
  //     val i = Intent(context, OffRadioReceiver::class.java)
  //     val pi = PendingIntent.getBroadcast(context, 0, i, 0)
  //     am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), (1000 * 10 * 10).toLong(), pi) // Millisec * Second * Minute
  // }
}