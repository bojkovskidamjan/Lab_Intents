package mk.ukim.finki.mpip.lab_intents

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ImplicitActivity : AppCompatActivity() {

    private lateinit var textViewActivities: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_implicit)

        val arrayAdapter: ArrayAdapter<*>
        val mainIntent = Intent(Intent.ACTION_MAIN, null)
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER)
        val pkgAppsList = packageManager.queryIntentActivities(mainIntent, 0)

        var activities = arrayOf<String>()
        for (i in pkgAppsList.indices) {
            activities += pkgAppsList[i].activityInfo.name
        }

        var textViewActivities = findViewById<ListView>(R.id.textViewActivities)
        arrayAdapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, activities)
        textViewActivities.adapter = arrayAdapter

    }
}