package drugaya.astrajan.radio.rossiya_app.util
import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import androidx.core.app.NotificationCompat
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import drugaya.astrajan.radio.MainActivity
import drugaya.astrajan.radio.R
import drugaya.astrajan.radio.assets.GetListRadioStations

class App: Application() {
    companion object{
        lateinit var notification: Notification
        var player: SimpleExoPlayer? = null

        fun playExoplayer(context: Context){
            val playUrl = sharedPreferences.getString("stationName", "http://89.179.72.53:8070/live").toString()
            player!!.setMediaItem(MediaItem.fromUri(playUrl))
            player!!.prepare()
            player!!.play()
        }

        fun stopRadio(){
            if( player != null ){
                if(player!!.isPlaying){ player!!.stop() }
            }
        }
        lateinit var sharedPreferences: SharedPreferences
        lateinit var editor: SharedPreferences.Editor
        var listNamesStations = mutableListOf<String>()
        val listUrlStations = mutableListOf<String>()
    }

    @SuppressLint("CommitPrefEdits")
    override fun onCreate(){
        super.onCreate()
        player = SimpleExoPlayer.Builder(this ).build()

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

        sharedPreferences = getSharedPreferences("drugaya-astarjan", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        val radioName = sharedPreferences.getString("radio", "none").toString()
        if (radioName == "none"){ editor.putString("radio", "Другая Астрахань"); editor.apply() }

        GetListRadioStations.requestData()
    }
}