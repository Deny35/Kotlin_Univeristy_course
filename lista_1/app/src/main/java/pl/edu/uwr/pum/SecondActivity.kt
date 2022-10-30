package pl.edu.uwr.pum

import android.content.Intent
import android.content.Intent.CATEGORY_BROWSABLE
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    private val textQuestion: TextView by lazy{findViewById(R.id.question)}
    private var question:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        if(savedInstanceState != null){

        }
        question = intent.getStringExtra("Question").toString()
        findViewById<Button>(R.id.button).setOnClickListener{
            val url = "https://www.google.com/search?q=" + question.replace("\\s".toRegex(), "+")
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply{
                addCategory(CATEGORY_BROWSABLE)
            }

            if (intent.resolveActivity(packageManager) != null)
                startActivity(intent)
        }
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState.putString("Question", question)
    }
}