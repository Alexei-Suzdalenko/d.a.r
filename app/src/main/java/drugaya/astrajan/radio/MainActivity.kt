package drugaya.astrajan.radio
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import drugaya.astrajan.radio.rossiya_app.util.App.Companion.player
import drugaya.astrajan.radio.rossiya_app.util.App.Companion.stopRadio
import drugaya.astrajan.radio.rossiya_app.util.ServiceRadio
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        start.setOnClickListener {
            ContextCompat.startForegroundService(this, Intent(this, ServiceRadio::class.java))
            start.visibility = View.GONE; stop.visibility = View.VISIBLE
        }

        stop.setOnClickListener {
            stopService(Intent(this, ServiceRadio::class.java))
            stopRadio()
            start.visibility = View.VISIBLE; stop.visibility = View.GONE
        }
    }

    override fun onStart() {
        super.onStart()
        if(player != null){ if(player!!.isPlaying){ start.visibility = View.GONE; stop.visibility = View.VISIBLE }
            if(player!!.isPlaying){ start.visibility = View.GONE; stop.visibility = View.VISIBLE }
            if(player!!.isPlaying){ start.visibility = View.GONE; stop.visibility = View.VISIBLE }
        }
    }
}
