package drugaya.astrajan.radio
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Window
import android.view.WindowInsets
import android.view.WindowManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import drugaya.astrajan.radio.databinding.IndexPagesBinding
import drugaya.astrajan.radio.rossiya_app.util.App.Companion.navController
import android.media.RingtoneManager

import android.media.Ringtone
import android.net.Uri
import android.os.PowerManager
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import java.lang.Exception
class MainActivity : AppCompatActivity() {
    private lateinit var binding: IndexPagesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) { window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else { window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN) }
        supportActionBar?.hide()
        binding = IndexPagesBinding.inflate(layoutInflater)
        setContentView( binding.root )
        val navView: BottomNavigationView = binding.navView
        navController = findNavController(R.id.nav_host_fragment_index_pages)
        val appBarConfiguration = AppBarConfiguration(setOf( R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        // desactivar optimizacion de la bateria
        try{
            val powerManager = applicationContext.getSystemService(POWER_SERVICE) as PowerManager
            val packageName = "drugaya.astrajan.radio"
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val i = Intent()
                if (!powerManager.isIgnoringBatteryOptimizations(packageName)) { i.action = Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS; i.data = Uri.parse("package:$packageName");startActivity(i) }
            }
        } catch (e: Exception){ Toast.makeText(this, resources.getString(R.string.error_app), Toast.LENGTH_SHORT).show() }



        // navController.navigate(R.id.navigation_notifications)
    }

    fun goHomeFragent(){ navController.navigate( R.id.navigation_home ) }
}