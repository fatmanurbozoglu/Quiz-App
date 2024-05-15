package com.example.quizapp.helpers

interface QuizManager {
    fun moveToNextQuestion():Boolean
    fun getScore(): Int
    fun getTotalQuestions(): Int
    fun getFalseAnswers(): Int
    fun calculateSuccessRate(score: Int, totalQuestions: Int): String
    fun resetQuiz()
    fun getCurrentQuestionsIndex(): Int
}