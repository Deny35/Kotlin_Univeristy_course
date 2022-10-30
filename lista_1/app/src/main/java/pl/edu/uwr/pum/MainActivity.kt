package pl.edu.uwr.pum

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Question(
    val question: String,
    val correctAnswer: Int
)

object Questions {
    val questions = listOf<Question>(
        Question("Czy Einstein urodził się w roku 1879?",1),
        Question("Czy stanów skupienia jest 3?",1),
        Question("Czy to jest prawidłowe równanie na drogę s=t/v?",0),
        Question("Czy zakres widzialnych fal światła 380nm 780nm?",1),
        Question("Czy elektron to najmniejsza cząsteczka?",0),
        Question("Wartośc przyśpieszenia ziemskiego wynosi 11m/s^2?",0),
        Question("Amper to jednostka natężenia prądu?",1),
        Question("We wzorze C=Q*U, U oznacza ładunek?",0),
        Question("Czy predkość światła jest większa od prędkości dźwięku?",1),
        Question("Czy woda jest ciekła?",1),
        Question("END",0)


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
    private var correctAnswer: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState != null){
            point = savedInstanceState.getInt("gamePoint")
            currentPosition = savedInstanceState.getInt("gameCurrentPosition")
            cheatUsed = savedInstanceState.getInt("gameCheatUsed")
        }
        ansYes.setOnClickListener{ checkAnswer(1)}
        ansNo.setOnClickListener{ checkAnswer(0)}
        ansCheat.setOnClickListener{ checkAnswer(2)}
        setView()


    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState.putInt("gamePoint", point)
        savedInstanceState.putInt("gameCurrentPosition", currentPosition)
        savedInstanceState.putInt("gameCheatUsed", cheatUsed)

    }

    private fun checkAnswer(ans: Int){
        val questions = questions[currentPosition - 1]
        if(ans == questions.correctAnswer){
            point += 10
            correctAnswer += 1
            currentPosition += 1
        }
        if(ans != questions.correctAnswer && ans != 2){
            currentPosition += 1
        }
        if(ans==2){
            if(cheatUsed > 0){
                point -= 15
            }
            cheatUsed += 1
            currentPosition += 1
            val intentS = Intent(this, SecondActivity::class.java)
            intentS.putExtra("Question", questions.question)
            startActivity(intentS)
        }
        if(currentPosition == 11){
            val intentT = Intent(this, ThirdActivity::class.java)
            intentT.putExtra("Points", point)
            intentT.putExtra("Correct", correctAnswer)
            intentT.putExtra("Cheat", cheatUsed)
            startActivity(intentT)
        }
        else{
            setView()
        }
    }

    private fun setView() {
        val question = questions[currentPosition-1]

        textQuestion.text = question.question
        textCheat.text = "Cheat:" + cheatUsed.toString()
        textPoint.text = "Points: " + point.toString()
    }



}
