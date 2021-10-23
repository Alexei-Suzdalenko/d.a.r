package drugaya.astrajan.radio.rossiya_app.util
import android.app.Service
import android.content.Intent
import android.os.IBinder
import drugaya.astrajan.radio.rossiya_app.util.App.Companion.playExoplayer
import drugaya.astrajan.radio.rossiya_app.util.App.Companion.stopRadio

class ServiceRadio: Service() {
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        playExoplayer( this )
        startForeground(11, App.notification)
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        stopRadio()
    }

    override fun onBind(p0: Intent?): IBinder? { return null }
}