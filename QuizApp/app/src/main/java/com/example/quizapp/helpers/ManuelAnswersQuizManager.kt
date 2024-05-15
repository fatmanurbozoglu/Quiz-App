@file:Suppress("NAME_SHADOWING")

package com.example.quizapp.helpers

import com.example.quizapp.models.QuestionsManuelAnswers

object ManuelAnswersQuizManager:QuizManager {
    var currentQuestionIndex = 0
    private var score = 0
    private var falseAnswers = 0
    private var unansweredQuestions = 0

    private val manuelAnswersQuestions = arrayOf(
        QuestionsManuelAnswers("Kotlin, hangi programlama diliyle uyumludur?", "Java"),
        QuestionsManuelAnswers("Kotlin'de null değerlerle nasıl başa çıkılır?", "Null guvenligi"),
        QuestionsManuelAnswers("Kotlin, hangi şirket tarafından geliştirilmiştir?", "JetBrains"),
        QuestionsManuelAnswers("Kotlin'in ilk sürümü ne zaman piyasaya sürüldü?", "2011"),
        QuestionsManuelAnswers("Kotlin'de bir değişkenin değerini değiştirmek için hangi anahtar kelimeyi kullanırız?", "var")
    )
    fun getCurrentQuestions():QuestionsManuelAnswers{
        return manuelAnswersQuestions[currentQuestionIndex]
    }
    override fun getCurrentQuestionsIndex(): Int {
        return currentQuestionIndex
    }
    override fun moveToNextQuestion(): Boolean {
        currentQuestionIndex++
        return currentQuestionIndex < manuelAnswersQuestions.size
    }

    fun checkAnswers(answer: String): Boolean {
        val currentQuestion = getCurrentQuestions()
        val isCorrect = answer.trim().equals(currentQuestion.answer.trim(), ignoreCase = true)

         if (isCorrect) {
            // dogru
            score++
        }else{
            // yanlis
            falseAnswers++
        }
        return isCorrect
    }
    override fun getScore(): Int {
        return score
    }
    override fun getFalseAnswers(): Int {
        return falseAnswers
    }
    override fun getTotalQuestions(): Int {
        return manuelAnswersQuestions.size
    }
    override fun resetQuiz(){
        score = 0
        falseAnswers = 0
        currentQuestionIndex = 0
        unansweredQuestions = 0
    }
    override fun calculateSuccessRate(score: Int, totalQuestions: Int): String {
        val totalQuestions = getTotalQuestions()
        val correctAnswer = getScore()
        val successRate = (correctAnswer.toFloat() / totalQuestions) * 100
        return "%"+ String.format("%.1f", successRate)
    }
}