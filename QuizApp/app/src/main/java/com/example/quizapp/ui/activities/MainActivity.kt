package com.example.quizapp.ui.activities

import android.content.Intent
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.style.CharacterStyle
import android.view.View
import android.widget.TextView
import com.example.quizapp.R
import com.example.quizapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.trueFalseButton.setOnClickListener {trueFalse(it)}
        binding.fourOptionsButton.setOnClickListener {fourOptions(it)}
        binding.manuelAnswerButton.setOnClickListener {manuelAnswers(it)}

       /* val gradientDrawable = GradientDrawable(
            GradientDrawable.Orientation.LEFT_RIGHT,
            intArrayOf(Color.RED,Color.YELLOW, Color.GREEN)
        )
        binding.textView.background = gradientDrawable*/
        // text rengini gecisli yapma
        val text = resources.getString(R.string.str_quiz)
        val spannableString = SpannableString(text)
        val gradient = LinearGradient(1f, 0f, binding.textView.paint.measureText(text),
            1f, intArrayOf(Color.GREEN,Color.YELLOW,Color.RED), null, Shader.TileMode.MIRROR)
        val span = object : CharacterStyle() {
            override fun updateDrawState(tp: TextPaint) {
                tp.shader = gradient
            }
        }
        spannableString.setSpan(span, 0, text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.textView.text = spannableString
    }

    private fun trueFalse(view: View){
        val intent = Intent(this, TrueFalseActivity::class.java)
        startActivity(intent)
    }
    private fun fourOptions(view: View){
        val intent = Intent(this, FourOptionsActivity::class.java)
        startActivity(intent)
    }
    private fun manuelAnswers(view: View){
        val intent = Intent(this, ManuelAnswersActivity::class.java)
        startActivity(intent)
    }
}

