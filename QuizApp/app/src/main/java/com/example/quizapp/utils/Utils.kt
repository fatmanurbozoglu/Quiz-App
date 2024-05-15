package com.example.quizapp.utils

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import androidx.appcompat.content.res.AppCompatResources
import com.example.quizapp.R
import com.example.quizapp.databinding.DialogAnswerBinding
import com.example.quizapp.helpers.QuizManager
import com.example.quizapp.ui.activities.ResultActivity

object Utils {
    fun openDialog(activity: Activity, answer:Boolean, callback:()->Unit,resultString: String,quizManager: QuizManager){
        val dialogBinding = DialogAnswerBinding.inflate(activity.layoutInflater)
        val dialog = Dialog(activity)
        dialog.setContentView(dialogBinding.root)
        dialogBinding.buttonNext.setOnClickListener {
            if (quizManager.moveToNextQuestion()) {
                callback()
            } else {
                openResultActivity(activity,resultString)
            }
            dialog.dismiss()
            return@setOnClickListener
        }

        if (answer){
            dialogBinding.textViewDialog.text = activity.resources.getString(R.string.true_answer_message)
            dialogBinding.imageViewDialog.background = AppCompatResources.getDrawable(activity,R.drawable.trueicon)
            dialog.setTitle(activity.resources.getString(R.string.str_true))
            dialogBinding.textViewDialog.setTextColor(AppCompatResources.getColorStateList(activity,R.color.green))
        }else{
            dialogBinding.textViewDialog.text= activity.resources.getString(R.string.false_answer_message)
            dialogBinding.imageViewDialog.background = AppCompatResources.getDrawable(activity,R.drawable.falseicon)
            dialog.setTitle(activity.resources.getString(R.string.str_false))
            dialogBinding.textViewDialog.setTextColor(AppCompatResources.getColorStateList(activity,R.color.red))
        }

        dialog.setCancelable(false)
        dialog.show()
    }

    fun openResultActivity(activity: Activity,resultString:String){
        val intent = Intent(activity, ResultActivity::class.java)
        intent.putExtra("sonuc_ekranı", resultString)
        activity.startActivity(intent)
        activity.finish()
    }
}