package drugaya.astrajan.radio
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import drugaya.astrajan.radio.rossiya_app.util.App.Companion.player
import drugaya.astrajan.radio.rossiya_app.util.App.Companion.stopRadio
import drugaya.astrajan.radio.rossiya_app.util.ServiceRadio
import kotlinx.android.synthetic.main.activity_main.*
class MainActivity : AppCompatActivity() { // // video_view.player = player
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
     //   if(player != null){ if( player!!.isPlaying ){ start.visibility = View.GONE; stop.visibility = View.VISIBLE; } }
    }
}
