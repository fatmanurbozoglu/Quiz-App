package com.example.quizapp.helpers

import com.example.quizapp.models.QuestionsTrueFalse

object TrueFalseQuizManager:QuizManager{

    private var currentQuestionIndex = 0
    private var score = 0
    private var falseAnswers = 0
    private var unansweredQuestions = 0

    private var trueFalseQuestions = arrayOf(
        QuestionsTrueFalse("Kotlin, Android uygulama geliştirmek için kullanılan bir programlama dilidir.", true),
        QuestionsTrueFalse("Kotlin, Java'dan daha hızlı çalışan bir programlama dilidir. ", false),
        QuestionsTrueFalse("Kotlin, nesne yönelimli bir programlama dilidir.",true),
        QuestionsTrueFalse("Kotlin, Java ile geriye dönük uyumlu değildir. ",false),
        QuestionsTrueFalse("Kotlin, daha az kod yazmayı sağlayan kısa ve anlaşılır sözdizimi ile bilinir.",true),
        QuestionsTrueFalse("Kotlin'de, 'lateinit' anahtar kelimesi, bir değişkenin sonradan atanacağını belirtmek için kullanılır.",true),
        QuestionsTrueFalse("Kotlin'de, bir değişkenin değerini değiştirmek için 'val' anahtar kelimesi kullanılır.",false),
        QuestionsTrueFalse("Kotlin, yalnızca Android için geliştirilmiş bir dil olduğu için diğer platformlarda kullanılamaz.",false),
        QuestionsTrueFalse("Kotlin, Java'nın yerini tamamen almıştır ve artık Java kullanılmamaktadır.",false),
        QuestionsTrueFalse("Kotlin, Android uygulamalarında daha düşük bellek tüketimi sağlar.",true)
    )


    fun getCurrentQuestions(): QuestionsTrueFalse {
        return trueFalseQuestions[currentQuestionIndex]
    }
    override fun getCurrentQuestionsIndex(): Int {
        return currentQuestionIndex
    }
    fun checkAnswers(answer: Boolean): Boolean {
        val currentQuestion = getCurrentQuestions()
        return if (answer == currentQuestion.answer) {
            // dogru
            score++
            true
        }else{
            // yanlis
            falseAnswers++
            false
        }
    }
    override fun moveToNextQuestion(): Boolean {
        currentQuestionIndex++
        return currentQuestionIndex < trueFalseQuestions.size
    }
    override fun resetQuiz() {
        currentQuestionIndex = 0
        score = 0
        falseAnswers = 0
        unansweredQuestions = 0
    }
    override fun getScore(): Int {
        return score
    }
    override fun getTotalQuestions(): Int {
        return trueFalseQuestions.size
    }
    override fun getFalseAnswers(): Int {
        return falseAnswers
    }
    override fun calculateSuccessRate(score: Int, totalQuestions: Int): String {
        val totalQuestions = getTotalQuestions()
        val correctAnswers = getScore()
        val successRate = (correctAnswers.toFloat() / totalQuestions) * 100
        return "%"+ String.format("%.1f", successRate)
    }
}