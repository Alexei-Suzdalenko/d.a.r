package drugaya.astrajan.radio.components.files
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.core.content.ContextCompat.startActivity
import drugaya.astrajan.radio.MainActivity
import drugaya.astrajan.radio.R
import drugaya.astrajan.radio.assets.GetListRadioStations
import drugaya.astrajan.radio.rossiya_app.util.App.Companion.editor
import kotlinx.android.synthetic.main.fragment_notifications.view.*
class AddButtonFunctionlity(val activity: Activity, val view: View) {

    fun setButtonBottomFunctionality(){

        view.comment.setOnClickListener { MainActivity().commentThisApp(activity) }

        view.es_textview.setOnClickListener {
            editor.putString("language", "es"); editor.apply()
            GetListRadioStations.requestData(activity)
        }

        view.ru_textview.setOnClickListener {
            editor.putString("language", "ru"); editor.apply()
            GetListRadioStations.requestData(activity)
        }
    }

}