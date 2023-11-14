package mk.ukim.finki.mpip.lab_intents

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class ExplicitActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    private lateinit var btnSubmit: Button
    private lateinit var btnCancel: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explicit)

        editText = findViewById(R.id.editText)
        btnSubmit = findViewById(R.id.btnSubmit)
        btnCancel = findViewById(R.id.btnCancel)

        btnSubmit.setOnClickListener(){ _ ->
            Intent().let { intent ->
                intent.putExtra("textValue", editText.text.toString())
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }

        btnCancel.setOnClickListener(){ _ ->
            Intent().let {
                setResult(Activity.RESULT_CANCELED, intent)
                finish()
            }
        }
    }
}