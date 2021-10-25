package drugaya.astrajan.radio.assets
import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import drugaya.astrajan.radio.R
import drugaya.astrajan.radio.rossiya_app.util.App
import drugaya.astrajan.radio.rossiya_app.util.App.Companion.listNamesStations

class RadioStationAdapter(private val c: Context): BaseAdapter() {
    override fun getCount(): Int { return  listNamesStations.size }
    override fun getItem(position: Int): Any { return listNamesStations[position] }
    override fun getItemId(position: Int): Long { return position.toLong() }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val layoutView = LayoutInflater.from(c).inflate(R.layout.radio_item  , parent, false)

        layoutView.findViewById<TextView>(R.id.station_name_textview).text = listNamesStations[position]

        return layoutView
    }
}








// Picasso.get().load(jsonResponses[p0].getString("image")).into(imageStation)