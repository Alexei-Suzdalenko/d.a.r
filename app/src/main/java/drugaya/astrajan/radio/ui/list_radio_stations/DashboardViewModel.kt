package drugaya.astrajan.radio.ui.list_radio_stations
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {
  //  thread {
  //      val json = try { URL(url).readText() } catch (e: Exception) { return@thread }
  //      runOnUiThread { displayOrWhatever(json) }
  //  }

    val text: LiveData<String> = MutableLiveData<String>().apply { value = "This is dashboard Fragment" }

    val otherValue: LiveData<List<Any>> =  Example.exampleData()
}


object Example {
    fun exampleData() : MutableLiveData<List<Any>>{
        val mutableLiveData =  MutableLiveData<List<Any>>()
        val listItem = mutableListOf<Any>()
        listItem.add( 1 ); listItem.add( "data string" )

        mutableLiveData.value = listItem

//     Thread {
//         Log.d("tag", "==> start ")
//         try { val respose = URL("https://alexei-suzdalenko.github.io/rus-radio/rus-radio.js").readText()
//                val jsonObject = JSONObject( respose )
//                val arrayNames: JSONArray = jsonObject.get("name") as JSONArray
//                val arrayUrls: JSONArray = jsonObject.get("uri") as JSONArray

//             // Log.d("tag", "response ${respose.toString()}")

//             Log.d("tag", "response ${arrayNames.toString()}")
//             Log.d("tag", "response ${arrayUrls.toString()}")


//         } catch (e: Exception) { Log.w("tag", "ERROR MESSAGE " + e.message.toString()) }
//         listItem.add("function")
//         Log.d("tag", "==> stop ")
//     }.start()

        return mutableLiveData
    }
}