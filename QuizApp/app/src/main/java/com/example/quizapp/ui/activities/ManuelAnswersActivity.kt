package com.example.quizapp.ui.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.quizapp.databinding.ActivityManuelAnswersBinding
import com.example.quizapp.helpers.AppConfig
import com.example.quizapp.helpers.ManuelAnswersQuizManager
import com.example.quizapp.utils.Utils

class ManuelAnswersActivity : AppCompatActivity() {
    private lateinit var binding: ActivityManuelAnswersBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_manuel_answers)
        binding = ActivityManuelAnswersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        updateQuestion()
        binding.button.setOnClickListener { checkAnswer()}
    }


    private fun checkAnswer(){
        val userInput = binding.editText.text.toString().trim()
        if (TextUtils.isEmpty(userInput)) {
            Toast.makeText(this, "Please enter a value.", Toast.LENGTH_SHORT).show()
        } else {
            val isCorrect = ManuelAnswersQuizManager.checkAnswers(userInput)
            if (isCorrect) {
                // cevap dogruysa
                Utils.openDialog(this,true,::updateQuestion,AppConfig.MANUEL_ANSWERS,ManuelAnswersQuizManager)
            } else {
                // cevap yanlıssa
                Utils.openDialog(this,false,::updateQuestion,AppConfig.MANUEL_ANSWERS,ManuelAnswersQuizManager)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateQuestion(){
        binding.textView.text = "${ManuelAnswersQuizManager.getCurrentQuestionsIndex() + 1}. Soru"
        val currentQuestion = ManuelAnswersQuizManager.getCurrentQuestions()
        binding.questionTextView.text = currentQuestion.question
        binding.editText.setText("")
    }
    /*private fun dialogTrue(){
        val dialogBinding = layoutInflater.inflate(R.layout.dialog_true_answer, null)

        val builder = AlertDialog.Builder(this)
        val dialog = builder.create()
        val buttonTrue = dialogBinding.findViewById<Button>(R.id.buttonTrue)
        buttonTrue.setOnClickListener {
            if (ManuelAnswersQuizManager.moveToNextQuestion()) {
                updateQuestion()
            } else {
                showResult()
            }
            dialog.dismiss()
            return@setOnClickListener
        }
        dialog.setView(dialogBinding)
        dialog.setTitle(R.string.str_true)
        dialog.setCancelable(false)
        //dialog.window?.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        dialog.show()
    }
    @SuppressLint("MissingInflatedId")
    fun dialogFalse(){
        val dialogBinding = layoutInflater.inflate(R.layout.dialog_false_answer, null)

        val builder = AlertDialog.Builder(this)
        val dialog = builder.create()
        val buttonFalse = dialogBinding.findViewById<Button>(R.id.buttonFalse)

        buttonFalse.setOnClickListener {
            if (ManuelAnswersQuizManager.moveToNextQuestion()) {
                updateQuestion()
            } else {
                showResult()
            }
            dialog.dismiss()
            return@setOnClickListener
        }
        dialog.setTitle(R.string.str_false)
        dialog.setView(dialogBinding)
        dialog.setCancelable(false)
        //dialog.window?.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        dialog.show()
    }*/
}