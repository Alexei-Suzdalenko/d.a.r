package drugaya.astrajan.radio.assets
import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.core.content.ContextCompat
import drugaya.astrajan.radio.MainActivity
import drugaya.astrajan.radio.R
import drugaya.astrajan.radio.rossiya_app.util.App
import drugaya.astrajan.radio.rossiya_app.util.WaitAlarmService
import java.lang.Exception

class AutoStart: BroadcastReceiver() {
    // var alarm: OffRadioReceiver = OffRadioReceiver()

    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    override fun onReceive(context: Context?, intent: Intent?) {

        // al reiniciar el telefono abrimos la radio para que le usuario no lo olvide que tiene la app
        try{
            val wakeIntent = Intent()
            wakeIntent.setClassName("drugaya.astrajan.radio", "drugaya.astrajan.radio.MainActivity")
            wakeIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context!!.startActivity(wakeIntent)
        } catch (e: Exception){ Toast.makeText(context, context!!.resources.getString(R.string.error_app), Toast.LENGTH_SHORT).show() }

        // comprobar que esta instalada la alarma y si no esta caducada, si es asi instalarla mas adelante
        try{
           checkIfAlarmStateIsEnabledAndNoAutOfDate(context!!)
        } catch (e: Exception){ Toast.makeText(context, context!!.resources.getString(R.string.error_app), Toast.LENGTH_SHORT).show() }


        // aun no necesito iniciar ninguna alarma
        // if (intent!!.action.equals(Intent.ACTION_BOOT_COMPLETED)) { alarm.setAlarm( context!! ) }
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    private fun checkIfAlarmStateIsEnabledAndNoAutOfDate(context: Context){
        if ( App.sharedPreferences.getString("alarm", "").toString() == "enabled"){
            var alarmSettedTime = App.sharedPreferences.getLong("alarm_time", 0).toLong()

            // si la alarma esta caducada, cambiamos la hora por mas actualizada y instalamos la alarma
            if (alarmSettedTime < System.currentTimeMillis()){
                alarmSettedTime += 86400000
                App.editor.putLong("alarm_time", alarmSettedTime)
                App.editor.apply()
                checkIfAlarmStateIsEnabledAndNoAutOfDate(context)
            } else {
                val intent = Intent(context, OffRadioReceiver::class.java); intent.putExtra("set_alarm", "set_alarm")
                val pendingIntent: PendingIntent
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    pendingIntent = PendingIntent.getActivity(context, 11, Intent(context, MainActivity::class.java), PendingIntent.FLAG_MUTABLE)
                } else {
                    pendingIntent = PendingIntent.getBroadcast(context, 2, intent, PendingIntent.FLAG_UPDATE_CURRENT)
                }
                val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
                if ( Build.VERSION.SDK_INT  >= Build.VERSION_CODES.M ) {
                    alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, alarmSettedTime, pendingIntent)
                } else {
                    alarmManager.setExact(AlarmManager.RTC_WAKEUP, alarmSettedTime, pendingIntent) }

                ContextCompat.startForegroundService(context, Intent(context, WaitAlarmService::class.java))
                Toast.makeText(context, "ALARM INSTALLED BY AUTOSTART", Toast.LENGTH_SHORT).show()
            }
        }
    }
}