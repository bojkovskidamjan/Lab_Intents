package mk.ukim.finki.mpip.lab_intents
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView
    private lateinit var btnGoToExplicitActivity: Button
    private lateinit var btnGoToImplicitActivity: Button
    private lateinit var btnSelectImage: Button

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
        if ( result.resultCode == Activity.RESULT_OK)
        {
            val data: Intent? = result.data
            textView.text = data?.getStringExtra("textValue")
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.txtView)
        btnGoToExplicitActivity = findViewById(R.id.btnGoToExplicitActivity)
        btnGoToImplicitActivity = findViewById(R.id.btnGoToImplicitActivity)
        btnSelectImage = findViewById(R.id.btnSelectImage)


        btnGoToExplicitActivity.setOnClickListener() {
            val explIntent = Intent(this, ExplicitActivity::class.java)
            resultLauncher.launch(explIntent)
        }

        btnGoToImplicitActivity.setOnClickListener { _ ->
            Intent().apply {
                action = "mk.ukim.finki.mpip.IMPLICIT_ACTION"
                type = "text/plain"
            }.let { intent ->
                startActivity(Intent.createChooser(intent, "Choose the app for your intent"))
            }
        }

        btnSelectImage.setOnClickListener {
            val gallery = Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(Intent.createChooser(gallery, "Open image with..."), 100)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == 100) {
            val previewIntent = Intent(Intent.ACTION_VIEW)
            previewIntent.setDataAndType(data?.data, "image/*")
            startActivity(previewIntent)
        }
    }


}