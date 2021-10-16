package drugaya.astrajan.radio
import android.annotation.SuppressLint
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import drugaya.astrajan.radio.databinding.IndexPagesBinding
class IndexPages : AppCompatActivity() {
    private lateinit var binding: IndexPagesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = IndexPagesBinding.inflate(layoutInflater)
        setContentView( binding.root )
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_index_pages)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        // navController.navigate(R.id.navigation_notifications)
    }
}