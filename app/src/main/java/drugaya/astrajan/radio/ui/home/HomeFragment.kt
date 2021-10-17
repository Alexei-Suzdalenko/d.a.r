package drugaya.astrajan.radio.ui.home
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import drugaya.astrajan.radio.databinding.FragmentHomeBinding
import drugaya.astrajan.radio.rossiya_app.util.App
import drugaya.astrajan.radio.rossiya_app.util.App.Companion.player
import drugaya.astrajan.radio.rossiya_app.util.ServiceRadio
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var mInterstitialAd: InterstitialAd? = null
    private val adRequest: AdRequest = AdRequest.Builder().build()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View { // ca-app-pub-7286158310312043/2557923222 ca-app-pub-3940256099942544/1033173712
        chargeAdds()
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.start.setOnClickListener {
            ContextCompat.startForegroundService(requireActivity().applicationContext, Intent(requireActivity().applicationContext, ServiceRadio::class.java))
            binding.start.visibility = View.GONE; binding.stop.visibility = View.VISIBLE
        }

        binding.stop.setOnClickListener {
            requireActivity().applicationContext.stopService(Intent( requireActivity().applicationContext, ServiceRadio::class.java ))
            App.stopRadio(); binding.start.visibility = View.VISIBLE; binding.stop.visibility = View.GONE
            if (mInterstitialAd != null) { mInterstitialAd?.show( requireActivity() ); chargeAdds() }
        }

        binding.textHome.text = App.sharedPreferences.getString("radio", "Другая Астрахань").toString()

        if(player != null){ if( player!!.isPlaying ) { binding.start.visibility = View.GONE; binding.stop.visibility = View.VISIBLE } }
        return root
    }


    override fun onDestroyView() { super.onDestroyView(); _binding = null; }

    private fun chargeAdds() {
        InterstitialAd.load( requireActivity().applicationContext, "ca-app-pub-7286158310312043/2557923222", adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdLoaded(interstitialAd: InterstitialAd) { mInterstitialAd = interstitialAd }
        })
    }
}

/*
Configurar el archivo app-ads.txt de tus aplicaciones
La iniciativa app-ads.txt contribuye a la lucha contra el fraude y protege los ingresos que consigues a través de los anuncios en las aplicaciones. Para configurar el archivo app-ads.txt de tus aplicaciones de AdMob, sigue estas instrucciones.

Si todavía no lo has hecho, crea un archivo app-ads.txt mediante las especificaciones de IAB Tech Lab.
Copia el siguiente fragmento de código y pégalo en tu archivo app-ads.txt:
content_copy
google.com, , DIRECT, f08c47fec0942fa0
Sube tu archivo app-ads.txt al dominio raíz del sitio web del desarrollador de la aplicación (por ejemplo: sampledomain.com/app-ads.txt). Asegúrate de que introduces el nombre del dominio tal como aparece en Google Play o en el App Store.
AdMob necesita, como mínimo, 24 horas para rastrear y verificar el archivo app-ads.txt.
Abre AdMob, ve a tus aplicaciones y comprueba el estado del archivo app-ads.txt en la pestaña correspondiente.


 */