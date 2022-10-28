package pl.edu.uwr.pum

import android.content.Intent
import android.content.Intent.CATEGORY_BROWSABLE
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.l1.R

class SecondActivity : AppCompatActivity() {
    private val textQuestion: TextView by lazy{findViewById(R.id.question)}
    //private val nextt: Button by lazy{findViewById(R.id.button)}


    fun prv(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        findViewById<Button>(R.id.button).setOnClickListener{
            val url = "http://panoramx.ift.uni.wroc.pl/~kdenys/lista8/"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply{
                addCategory(CATEGORY_BROWSABLE)
            }

            if (intent.resolveActivity(packageManager) != null)
                startActivity(intent)
        }
    }

    fun setText(){
        val question = intent.getStringExtra("Q")
        val intAns = intent.getIntExtra("A", 2)
        var strAns: String = ""
        textQuestion.text = question
        if(intAns==1){
            strAns = "YES"
        }
        if(intAns==0){
            strAns = "NO"
        }
        if(intAns==2) {
            strAns = "NULL"
        }
    }
}