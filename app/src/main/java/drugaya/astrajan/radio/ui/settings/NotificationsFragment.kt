package drugaya.astrajan.radio.ui.settings
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import drugaya.astrajan.radio.components.files.AddButtonFunctionlity
import drugaya.astrajan.radio.components.files.AutoOffFunction.setAutoOffFunctionality
import drugaya.astrajan.radio.components.files.SetAlarm
import drugaya.astrajan.radio.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment() {
    private lateinit var notificationsViewModel: NotificationsViewModel
    private var _binding: FragmentNotificationsBinding? = null

    private val binding get() = _binding!!
    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        notificationsViewModel = ViewModelProvider(this).get(NotificationsViewModel::class.java)
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setAutoOffFunctionality(root, requireActivity().applicationContext)
        SetAlarm( root , requireActivity().applicationContext ).setAlarmFunction()
        AddButtonFunctionlity(requireActivity(), root).setButtonBottomFunctionality()

    //  val textView: TextView = binding.textNotifications
    //  notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
    //      textView.text = it
    //  })
        return root
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}