package drugaya.astrajan.radio.ui.list_radio_stations
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import drugaya.astrajan.radio.MainActivity
import drugaya.astrajan.radio.assets.RadioStationAdapter
import drugaya.astrajan.radio.databinding.FragmentListBinding
import drugaya.astrajan.radio.rossiya_app.util.App
import drugaya.astrajan.radio.rossiya_app.util.App.Companion.editor
import drugaya.astrajan.radio.rossiya_app.util.ServiceRadio
class ListFragment : Fragment() {
    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        dashboardViewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentListBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val listView: ListView = binding.listView

        listView.adapter = RadioStationAdapter(requireActivity().applicationContext)
        listView.setOnItemClickListener { _, _, position, _ ->
            editor.putString("stationName", App.listNamesStations[position])
            editor.putString("playUrl", App.listUrlStations[position])
            editor.apply()
            ContextCompat.startForegroundService( requireActivity().applicationContext, Intent(requireActivity().applicationContext, ServiceRadio::class.java ))
            MainActivity().goHomeFragent()
        }

       // dashboardViewModel.text.observe(viewLifecycleOwner, Observer { textView.text = it })
      //  dashboardViewModel.otherValue.observeForever {
      //      Log.d("tag", "============> ${ it.toString() }")
      //  }

        return root
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}