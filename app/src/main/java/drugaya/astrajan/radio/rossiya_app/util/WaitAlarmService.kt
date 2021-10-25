package drugaya.astrajan.radio.rossiya_app.util
import android.app.Service
import android.content.Intent
import android.os.IBinder
import drugaya.astrajan.radio.rossiya_app.util.App.Companion.setNotificationAlarm


class WaitAlarmService : Service() {
    override fun onCreate() {
        super.onCreate()
         setNotificationAlarm(this)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForeground(7, App.notificationAlarm)
        return START_STICKY
    }


    override fun onBind(intent: Intent?): IBinder? { return null }


}