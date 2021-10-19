package drugaya.astrajan.radio.ui.settings
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.slider.RangeSlider
import drugaya.astrajan.radio.databinding.FragmentNotificationsBinding
import kotlinx.android.synthetic.main.fragment_notifications.view.*

class NotificationsFragment : Fragment() {
    private lateinit var notificationsViewModel: NotificationsViewModel
    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        notificationsViewModel = ViewModelProvider(this).get(NotificationsViewModel::class.java)
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val rangeSlider = root.range_slider
        val autoOffTextview = root.auto_off_textview
        rangeSlider.addOnSliderTouchListener(object : RangeSlider.OnSliderTouchListener{
            override fun onStartTrackingTouch(slider: RangeSlider) {
                val values = rangeSlider.values; autoOffTextview.text = values.toString()
                Toast.makeText( requireActivity().applicationContext, values.toString(), Toast.LENGTH_SHORT).show()
            }
            override fun onStopTrackingTouch(slider: RangeSlider) { }
        })

        autoOffTextview.setOnClickListener {
            Toast.makeText( requireActivity().applicationContext, "CLICK", Toast.LENGTH_SHORT).show()
            autoOffTextview.text = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
        }

    //  val textView: TextView = binding.textNotifications
    //  notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
    //      textView.text = it
    //  })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}