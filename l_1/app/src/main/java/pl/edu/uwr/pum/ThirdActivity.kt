package pl.edu.uwr.pum

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.system.exitProcess

class ThirdActivity : AppCompatActivity() {

    private val textPoints: TextView by lazy{findViewById(R.id.textPoints)}
    private val textCorrect: TextView by lazy{findViewById(R.id.textCorrect)}
    private val textCheat: TextView by lazy{findViewById(R.id.textCheat)}
    private val button: TextView by lazy{findViewById(R.id.end)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        val points = intent.getIntExtra("Points", 0 )
        val correct = intent.getIntExtra("Correct", 0 )
        val cheat = intent.getIntExtra("Cheat", 0 )

        button.setOnClickListener{ quitApp()}


        textPoints.text = "Poits: " + points.toString()
        textCorrect.text = "Correcr Answer: " + correct.toString()
        textCheat.text = "Cheat Used:" + cheat.toString()
    }

    fun quitApp() {
        finishAffinity()
        exitProcess(0)
        //finish()
    }

}
