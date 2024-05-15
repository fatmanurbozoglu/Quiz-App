package com.example.quizapp.ui.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quizapp.helpers.AppConfig
import com.example.quizapp.helpers.TrueFalseQuizManager
import com.example.quizapp.databinding.ActivityTrueFalseBinding
import com.example.quizapp.utils.Utils

open class TrueFalseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTrueFalseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_true_false)
        binding = ActivityTrueFalseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.trueButton.setOnClickListener { checkAnswer(true) }
        binding.falseButton.setOnClickListener { checkAnswer(false) }

        updateQuestion()
    }
    @SuppressLint("SetTextI18n")
    private fun updateQuestion(){
        binding.textView.text = "${TrueFalseQuizManager.getCurrentQuestionsIndex() + 1}. Soru"
        var currentQuestion = TrueFalseQuizManager.getCurrentQuestions()
        binding.questionTextView.text = currentQuestion.question
    }

    private fun checkAnswer(answer: Boolean){
        val isCorrect = TrueFalseQuizManager.checkAnswers(answer)
        if (isCorrect) {
            // cevap dogruysa
            Utils.openDialog(this,true,::updateQuestion,AppConfig.TRUE_FALSE,TrueFalseQuizManager)
        } else {
            // cevap yanlıssa
            Utils.openDialog(this,false,::updateQuestion,AppConfig.TRUE_FALSE,TrueFalseQuizManager)
        }
    }

}