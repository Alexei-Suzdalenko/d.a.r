package drugaya.astrajan.radio
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
    }

    fun goHomeFragent(){ navController.navigate( R.id.navigation_home ) }
}