package com.example.quizapp.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.quizapp.helpers.AppConfig
import com.example.quizapp.helpers.TrueFalseQuizManager
import com.example.quizapp.databinding.ActivityResultBinding
import com.example.quizapp.helpers.FourOptionsQuizManager
import com.example.quizapp.helpers.ManuelAnswersQuizManager


open class ResultActivity : AppCompatActivity(){
    private lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_result)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showResult()

        binding.mainMenuButton.setOnClickListener { mainMenu() }

    }
   /*     ****MENU****
   override fun onCreateOptionsMenu(menu: Menu?): Boolean {
       val menuInflater = menuInflater
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        QuizManager.resetQuiz()
        val intent =  Intent(this, MainActivity::class.java)
        startActivity(intent)
        return super.onOptionsItemSelected(item)
    }
    */
   /*private fun showResult() {
       val score = FourOptionsQuizManager.getScore()
       val totalQuestions = FourOptionsQuizManager.getTotalQuestions()
       val falseAnswers = FourOptionsQuizManager.getFalseAnswers()
       val successRate = FourOptionsQuizManager.successRate()

       binding.trueTextView.text = score.toString()
       binding.falseTextView.text = falseAnswers.toString()
       binding.successTextView.text = successRate

       Log.d("ResultActivity", "Score: $score, Total Questions: $totalQuestions, False Answers: $falseAnswers")
   }*/

    private fun showResult(){
        val sonuc = this.intent.getStringExtra("sonuc_ekranı")

        val trueFalseScore = TrueFalseQuizManager.getScore()
        val trueFalseTotalQuestions = TrueFalseQuizManager.getTotalQuestions()
        val trueFalseFalseAnswer = TrueFalseQuizManager.getFalseAnswers()
        val calculateSuccessRate = TrueFalseQuizManager.calculateSuccessRate(trueFalseScore,trueFalseTotalQuestions)

        val fourOptionsScore = FourOptionsQuizManager.getScore()
        val fourOptionsTotalQuestions = FourOptionsQuizManager.getTotalQuestions()
        val fourOptionsFalseAnswers = FourOptionsQuizManager.getFalseAnswers()
        val fourOptionsSuccessRate = FourOptionsQuizManager.calculateSuccessRate(fourOptionsScore,fourOptionsTotalQuestions)

        val manuelAnswersScore = ManuelAnswersQuizManager.getScore()
        val manuelAnswersTotalQuestions = ManuelAnswersQuizManager.getTotalQuestions()
        val manuelAnswersFalseAnswer = ManuelAnswersQuizManager.getFalseAnswers()
        val manuelAnswersSuccessRate = ManuelAnswersQuizManager.calculateSuccessRate(manuelAnswersScore,manuelAnswersTotalQuestions)

        when(sonuc){
            AppConfig.FOUR_OPTIONS ->{
                binding.trueTextView.text = fourOptionsScore.toString()
                binding.falseTextView.text = fourOptionsFalseAnswers.toString()
                binding.successTextView.text = fourOptionsSuccessRate

                Log.d("ResultActivityFourOptions", "Score: $fourOptionsScore, Total Questions: $fourOptionsTotalQuestions, False Answers: $fourOptionsFalseAnswers")
                return
            }
            AppConfig.TRUE_FALSE->{
                binding.trueTextView.text = trueFalseScore.toString()
                binding.falseTextView.text = trueFalseFalseAnswer.toString()
                binding.successTextView.text = calculateSuccessRate

                Log.d("ResultActivityTrueFalse", "Score: $trueFalseScore, Total Questions: $trueFalseTotalQuestions, False Answers: $trueFalseFalseAnswer")
                return
            }
            AppConfig.MANUEL_ANSWERS ->{
                binding.trueTextView.text = manuelAnswersScore.toString()
                binding.falseTextView.text = manuelAnswersFalseAnswer.toString()
                binding.successTextView.text = manuelAnswersSuccessRate

                Log.d("ResultActivityManuelAnswers", "Score: $manuelAnswersScore, Total Questions: $manuelAnswersTotalQuestions, False Answers: $manuelAnswersFalseAnswer")
                return
            }
        }
    }
     /*private fun showResult(){
          val score: Int
          val totalQuestions: Int
          val falseAnswers: Int

          if (TrueFalseQuizManager.getTotalQuestions() > 0) {

              score = TrueFalseQuizManager.getScore()
              totalQuestions = TrueFalseQuizManager.getTotalQuestions()
              falseAnswers = TrueFalseQuizManager.getFalseAnswers()
              binding.trueTextView.text = score.toString()
              binding.falseTextView.text = falseAnswers.toString()
              Log.d("ResultActivity", "Score: $score, Total Questions: $totalQuestions, False Answers: $falseAnswers")
              val successRate = TrueFalseQuizManager.calculateSuccessRate(score, totalQuestions)
              binding.successTextView.text = successRate

          } else if (FourOptionsQuizManager.getTotalQuestions() > 0){

              score = FourOptionsQuizManager.getScore()
              totalQuestions = FourOptionsQuizManager.getTotalQuestions()
              falseAnswers = FourOptionsQuizManager.getFalseAnswers()
              binding.trueTextView.text = score.toString()
              binding.falseTextView.text = falseAnswers.toString()
              Log.d("ResultActivity1", "Score: $score, Total Questions: $totalQuestions, False Answers: $falseAnswers")
              val successRate = FourOptionsQuizManager.successRate()
              binding.successTextView.text = successRate
          }
    }*/

    private fun mainMenu(){
        TrueFalseQuizManager.resetQuiz()
        FourOptionsQuizManager.resetQuiz()
        ManuelAnswersQuizManager.resetQuiz()
        val intent =  Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
