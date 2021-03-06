package drugaya.astrajan.radio
import android.annotation.SuppressLint
import android.content.Context
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
import drugaya.astrajan.radio.rossiya_app.util.App
import java.lang.Exception
class MainActivity : AppCompatActivity() {
    private lateinit var binding: IndexPagesBinding

    @SuppressLint("BatteryLife")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        supportActionBar?.hide()
        binding = IndexPagesBinding.inflate(layoutInflater)
        setContentView( binding.root )
        val navView: BottomNavigationView = binding.navView
        navController = findNavController(R.id.nav_host_fragment_index_pages)
        val appBarConfiguration = AppBarConfiguration(setOf( R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    // esto uso para hacer prueba de alarma ...
    //   App.editor.putLong("alarm_time", (1000).toLong())
    //   App.editor.apply()

        // desactivar optimizacion de la bateria
        try{
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            val pkg = packageName
            val pm = getSystemService(PowerManager::class.java)
            if (!pm.isIgnoringBatteryOptimizations(pkg)) {
                val i = Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS).setData(Uri.parse("package:$pkg"))
                startActivity(i)
            }
        }
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
    fun commentThisApp(context: Context){
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=drugaya.astrajan.radio"))
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }
    fun goToListRadiosStations(){ navController.navigate( R.id.navigation_dashboard ) }
}


// https://pasportnyy-stol.com/guvm-mvd-v-astrahanskoy-oblasti-adresa-podrazdeleniy/