package drugaya.astrajan.radio.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {


    val text: LiveData<String> = MutableLiveData<String>().apply { value = "This is dashboard Fragment" }


}