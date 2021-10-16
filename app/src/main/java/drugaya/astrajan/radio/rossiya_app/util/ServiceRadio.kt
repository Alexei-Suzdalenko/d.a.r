package drugaya.astrajan.radio.rossiya_app.util
import android.app.Service
import android.content.Intent
import android.os.IBinder
import drugaya.astrajan.radio.rossiya_app.util.App.Companion.playExoplayer

class ServiceRadio: Service() {
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForeground(11, App.notification)
        playExoplayer( this )
        return START_STICKY
    }

    override fun onBind(p0: Intent?): IBinder? { return null }
}