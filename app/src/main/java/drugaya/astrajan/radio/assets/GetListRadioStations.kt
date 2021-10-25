package drugaya.astrajan.radio.assets
import android.util.Log
import drugaya.astrajan.radio.rossiya_app.util.App.Companion.listNamesStations
import drugaya.astrajan.radio.rossiya_app.util.App.Companion.listUrlStations
import drugaya.astrajan.radio.rossiya_app.util.App.Companion.sharedPreferences
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL
import java.util.*

object GetListRadioStations {
    fun requestData(){
        var url = "https://alexei-suzdalenko.github.io/rus-radio/es.js"
        val currentSettedLanguage = sharedPreferences.getString("language", "none").toString()

        if( currentSettedLanguage == "es"){
            url = "https://alexei-suzdalenko.github.io/rus-radio/es.js"
        }

        if( currentSettedLanguage == "ru"){
            url = "https://alexei-suzdalenko.github.io/rus-radio/rus-radio.js"
        }

        if( currentSettedLanguage == "none"){
            var lang = ""
            try{ lang = Locale.getDefault().displayLanguage } catch (e: Exception){}
            if(lang.contains("рус")){ url = "https://alexei-suzdalenko.github.io/rus-radio/rus-radio.js" }
        }

        Thread {
            try {
                val respose = URL(url).readText()
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



// https://dolinger-web.ru/