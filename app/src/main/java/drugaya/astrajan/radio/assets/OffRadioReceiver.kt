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
import androidx.core.app.JobIntentService.enqueueWork
import androidx.legacy.content.WakefulBroadcastReceiver
import java.lang.Exception

class OffRadioReceiver : BroadcastReceiver () {
    lateinit var pm: PowerManager
    lateinit var wl: WakeLock

    @SuppressLint("InvalidWakeLockTag")
    override fun onReceive(context: Context?, intent: Intent?) {

        Toast.makeText(context, " !!!!!!!!!! !!!!!!!!!! start receiver !!!!!!!!!!", Toast.LENGTH_LONG).show() // For example

        pm = context!!.getSystemService(Context.POWER_SERVICE) as PowerManager
        wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "kio")
        wl.acquire(2*60*1000L )

        val wakeIntent = Intent()
        wakeIntent.setClassName("drugaya.astrajan.radio", "drugaya.astrajan.radio.MainActivity")
        wakeIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(wakeIntent)


        try {
            val notification: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val r = RingtoneManager.getRingtone(context, notification)
            r.play()
        } catch (e: Exception) { e.printStackTrace() }


        Toast.makeText(context, "Alarm !!!!!!!!!!", Toast.LENGTH_LONG).show() // For example
        wl.release()
    }

    fun setAlarm(context: Context) {
        val am = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val i = Intent(context, OffRadioReceiver::class.java)
        val pi = PendingIntent.getBroadcast(context, 0, i, 0)
        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), (1000 * 10 * 10).toLong(), pi) // Millisec * Second * Minute
    }
}