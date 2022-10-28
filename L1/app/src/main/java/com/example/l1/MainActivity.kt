package pl.edu.uwr.pum

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.l1.R
import com.example.l1.SecondActivity

class Question(
    val question: String,
    val correctAnswer: Int
)

object Questions {
    val questions = listOf<Question>(Question("A",1),
        Question("B",1),
        Question("C",1),
        Question("D",1),
        Question("E",1),
        Question("F",1),
        Question("G",1),
    )

}



class MainActivity : AppCompatActivity() {

    private val textQuestion: TextView by lazy{findViewById(R.id.show_question)}
    private val ansYes: Button  by lazy{findViewById(R.id.button_true)}
    private val ansNo: Button  by lazy{findViewById(R.id.button_false)}
    private val ansCheat: Button  by lazy{findViewById(R.id.button_cheat)}
    private val textPoint: TextView by lazy{findViewById(R.id.score_point)}
    private val textCheat: TextView by lazy{findViewById(R.id.score_cheat)}

    private val questions: List<Question> = Questions.questions
    private var currentPosition:Int = 1
    private var point:Int = 0
    private var cheatUsed:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setView()
        if(savedInstanceState != null){
            point = savedInstanceState.getInt("gamePoint")
            currentPosition = savedInstanceState.getInt("gameCurrentPosition")
            cheatUsed = savedInstanceState.getInt("gameCheatUsed")
        }
        ansYes.setOnClickListener{ checkAnswer(1)}
        ansNo.setOnClickListener{ checkAnswer(0)}
        ansCheat.setOnClickListener{ checkAnswer(2)}


    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState.putInt("gamePoint", point)
        savedInstanceState.putInt("gameCurrentPosition", currentPosition)
        savedInstanceState.putInt("gameCheatUsed", cheatUsed)

    }

    fun checkAnswer(ans: Int){
        val q = questions[currentPosition - 1]
        if(ans == q.correctAnswer){
            point += 10
            currentPosition += 1
            setView()
        }
        if(ans != q.correctAnswer && ans != 2){
            currentPosition += 1
            setView()
        }
        if(ans==2){
            if(cheatUsed == 0){
                cheatUsed += 1
            }
            else{
                point -= 15
            }
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setView() {
        val question = questions[currentPosition-1]
        textQuestion.text = question.question
        textCheat.text = "Cheat:" + cheatUsed.toString()
        textPoint.text = "Points: " + point.toString()

    }



}