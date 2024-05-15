package com.example.quizapp.ui.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quizapp.helpers.AppConfig
import com.example.quizapp.databinding.ActivityFourOptionsBinding
import com.example.quizapp.helpers.FourOptionsQuizManager
import com.example.quizapp.utils.Utils

open class FourOptionsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFourOptionsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_four_options)
        binding = ActivityFourOptionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.radioButton1.setOnClickListener { checkAnswer(0) }
        binding.radioButton2.setOnClickListener { checkAnswer(1) }
        binding.radioButton3.setOnClickListener { checkAnswer(2)}
        binding.radioButton4.setOnClickListener { checkAnswer(3) }

        displayQuestion()
        updateQuestion()
    }

    private fun displayQuestion(){
        var currentQuestion = FourOptionsQuizManager.fourOptionsQuestions[FourOptionsQuizManager.currentQuestionIndex]
        binding.questionTextView.text = currentQuestion.question
        binding.radioButton1.text = currentQuestion.options[0]
        binding.radioButton2.text = currentQuestion.options[1]
        binding.radioButton3.text = currentQuestion.options[2]
        binding.radioButton4.text = currentQuestion.options[3]
        binding.radioGroup.clearCheck()
    }

    @SuppressLint("SetTextI18n")
    private fun updateQuestion(){
        binding.textView.text = "${FourOptionsQuizManager.getCurrentQuestionsIndex() + 1}. Soru"
        val currentQuestions = FourOptionsQuizManager.getCurrentQuestions()
        binding.questionTextView.text = currentQuestions.question
        displayQuestion()
    }

    private fun checkAnswer(selectedOption: Int){
        val isAnswerCorrect = FourOptionsQuizManager.checkAnswers(selectedOption)
        if (isAnswerCorrect) {
            Utils.openDialog(this,true,::updateQuestion,AppConfig.FOUR_OPTIONS,FourOptionsQuizManager)
        } else {
            Utils.openDialog(this,false,::updateQuestion,AppConfig.FOUR_OPTIONS,FourOptionsQuizManager)
        }
    }


}