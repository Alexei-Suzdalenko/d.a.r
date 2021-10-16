package drugaya.astrajan.radio.rossiya_app.util
import android.app.*
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.core.app.NotificationCompat
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import drugaya.astrajan.radio.MainActivity
import drugaya.astrajan.radio.R

class App: Application() {
    companion object{
        lateinit var notification: Notification
        var player : SimpleExoPlayer? = null

        fun playExoplayer(context: Context){
            val content : ProgressiveMediaSource = ProgressiveMediaSource.Factory(DefaultDataSourceFactory(context , "Mozilla Firefox"))
                .createMediaSource(Uri.parse( "http://89.179.72.53:8070/live" ))
            player!!.prepare(content)
            player!!.playWhenReady = true
        }

        fun stopRadio(){ if(player!!.isPlaying){ player!!.stop() } }
    }

    override fun onCreate(){
        super.onCreate()

        player = ExoPlayerFactory.newSimpleInstance(this)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serChannel = NotificationChannel("drugaya-astrajan-radio", "name", NotificationManager.IMPORTANCE_LOW)
            val manager = getSystemService(NotificationManager::class.java)
            manager?.createNotificationChannel(serChannel)
        }

        notification = NotificationCompat.Builder(this, "drugaya-astrajan-radio" )
            .setContentTitle( getString( R.string.app_name) )
            .setSmallIcon( R.drawable.play_icon )
            .setContentIntent( PendingIntent.getActivity(this, 11, Intent(this, MainActivity::class.java), 0) )
            .build()
    }
}