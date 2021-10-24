package drugaya.astrajan.radio.assets
import android.app.Service
import android.content.Intent
import android.os.IBinder
class WaitAlarmService : Service() {
    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        return START_STICKY
    }

    override fun onStart(intent: Intent?, startId: Int) {

    }

    override fun onBind(intent: Intent?): IBinder? { return null }

    override fun onDestroy() {
        super.onDestroy()
    }

}