package drugaya.astrajan.radio.assets
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import java.lang.Exception

class AutoStart: BroadcastReceiver() {
    var alarm: OffRadioReceiver = OffRadioReceiver()

    override fun onReceive(context: Context?, intent: Intent?) {

        if (intent!!.action.equals(Intent.ACTION_BOOT_COMPLETED)) { alarm.setAlarm( context!! ) }

    }
}