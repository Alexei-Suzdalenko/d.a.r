package drugaya.astrajan.radio.assets
import android.util.Log
import drugaya.astrajan.radio.rossiya_app.util.App.Companion.listNamesStations
import drugaya.astrajan.radio.rossiya_app.util.App.Companion.listUrlStations
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL
object GetListRadioStations {
    fun requestData(){
        Thread {
            try {
                val respose = URL("https://alexei-suzdalenko.github.io/rus-radio/rus-radio.js").readText()
                val jsonObject = JSONObject( respose )

                val arrayNames: JSONArray = jsonObject.get("name") as JSONArray
                listNamesStations.clear()
                for (i in 0 until arrayNames.length()) { listNamesStations.add( arrayNames.get(i).toString() ) }

                val arrayUrls: JSONArray = jsonObject.get("uri") as JSONArray
                listUrlStations.clear()
                for (i in 0 until arrayNames.length()) { listUrlStations.add( arrayUrls.get(i).toString() ) }

            } catch (e: Exception) { Log.w("tag", "ERROR MESSAGE " + e.message.toString()) }
        }.start()
    }
}