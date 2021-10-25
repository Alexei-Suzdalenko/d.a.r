package drugaya.astrajan.radio.rossiya_app.util
import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Build
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.navigation.NavController
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import drugaya.astrajan.radio.MainActivity
import drugaya.astrajan.radio.R
import drugaya.astrajan.radio.assets.GetListRadioStations
import android.media.AudioManager
import android.media.AudioPlaybackCaptureConfiguration
import android.net.Uri
import android.os.PowerManager
import android.util.Log

class App: Application() {
    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var navController: NavController
        @SuppressLint("StaticFieldLeak")
        var startImageView: ImageView? = null
        @SuppressLint("StaticFieldLeak")
        var stopImageView: ImageView? = null
        lateinit var notification: Notification
        lateinit var notificationAlarm: Notification
        var player: SimpleExoPlayer? = null

        fun playExoplayer(context: Context){
            if( player == null) player = SimpleExoPlayer.Builder( context ).build()
            val radioName = sharedPreferences.getString("stationName", "Другая Астрахань").toString()
            notification = NotificationCompat.Builder(context, "drugaya-astrajan-radio" )
                .setContentTitle( radioName )
                .setSmallIcon( R.drawable.play_icon )
                .setContentIntent( PendingIntent.getActivity(context, 11, Intent(context, MainActivity::class.java), 0) )
                .build()
            val playUrl = sharedPreferences.getString("playUrl", "http://89.179.72.53:8070/live").toString()
            player!!.setMediaItem(MediaItem.fromUri( playUrl ))
            player!!.prepare()
            player!!.play()
        }

        fun stopRadio(){
            if( player != null ) { if (player!!.isPlaying) { player!!.stop() } }
            try { player?.stop() } catch (e: Exception){}
            try { player?.release() } catch (e: Exception){}
            player = null
        }

        lateinit var sharedPreferences: SharedPreferences
        lateinit var editor: SharedPreferences.Editor
        var listNamesStations = mutableListOf<String>()
        val listUrlStations = mutableListOf<String>()

      fun setNotificationAlarm(context: Context){
          val alarmInfo = sharedPreferences.getString("current_alarm", "").toString()
          val radioName = sharedPreferences.getString("stationName", "").toString()
          notificationAlarm = NotificationCompat.Builder(context, "notificationAlarm" )
              .setContentTitle("$radioName $alarmInfo")
              .setSmallIcon( R.drawable.alert_icon )
              .setContentIntent( PendingIntent.getActivity(context, 11, Intent(context, MainActivity::class.java), 0) )
              .build()
      }
    }

    @SuppressLint("CommitPrefEdits")
    override fun onCreate(){
        super.onCreate()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serChannel = NotificationChannel("drugaya-astrajan-radio", "name", NotificationManager.IMPORTANCE_LOW)
            val manager = getSystemService(NotificationManager::class.java)
            manager?.createNotificationChannel(serChannel)
        }
       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
           val serChannel2 = NotificationChannel("notificationAlarm", "name2", NotificationManager.IMPORTANCE_LOW)
           val manager = getSystemService(NotificationManager::class.java)
           manager?.createNotificationChannel(serChannel2)
       }

        sharedPreferences = getSharedPreferences("drugaya-astarjan", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        val radioName = sharedPreferences.getString("stationName", "none").toString()
        if (radioName == "none"){ editor.putString("radio", "Другая Астрахань"); editor.apply() }

        GetListRadioStations.requestData()
    }
}