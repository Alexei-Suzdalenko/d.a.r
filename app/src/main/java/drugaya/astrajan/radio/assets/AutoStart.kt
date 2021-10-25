package drugaya.astrajan.radio.assets
import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import android.widget.Toast
import drugaya.astrajan.radio.R
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


        // aun no necesito iniciar ninguna alarma
        // if (intent!!.action.equals(Intent.ACTION_BOOT_COMPLETED)) { alarm.setAlarm( context!! ) }

    }
}