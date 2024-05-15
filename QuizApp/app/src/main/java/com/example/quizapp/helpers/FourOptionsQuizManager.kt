package com.example.quizapp.helpers

import com.example.quizapp.models.QuestionsFourOptions

object FourOptionsQuizManager:QuizManager {

    var currentQuestionIndex = 0
    private var score = 0
    private var falseAnswers = 0
    var unansweredQuestions = 0

    var fourOptionsQuestions = arrayOf(
        QuestionsFourOptions("Kotlin, hangi platformlar için kullanılabilir?\n", arrayOf("Android","iOS", "Android ve iOS",
            "Sadece web tarayıcıları"),2),
        QuestionsFourOptions("Kotlin, hangi programlama paradigmalarını destekler?", arrayOf("Yalnızca Nesne Yönelimli Programlama (OOP)",
            "Yalnızca Yapısal Programlama", "Hem OOP hem de Fonksiyonel Programlama","Yalnızca Prosedürel Programlama"),2),
        QuestionsFourOptions("Kotlin'in temel olarak kullanıldığı alan hangisidir?", arrayOf("Oyun geliştirme","Veri analizi",
            "Yapay zeka","Mobil uygulama geliştirme"),3),
        QuestionsFourOptions("Kotlin'i geliştiren şirket hangisidir?", arrayOf("JetBrains", "Google","Apple",
            "Microsoft"),0),
        QuestionsFourOptions("Kotlin'in Java ile en önemli farkı nedir?", arrayOf("Kotlin, daha hızlı çalışır.",
            "Kotlin, null güvenliğine sahiptir.", "Kotlin, daha az bellek tüketir.","Kotlin, daha fazla kütüphane sunar."),1),
        QuestionsFourOptions("Kotlin hangi yıl piyasaya sürülmüştür?", arrayOf("2010","2011","2012","2013"),1),
        QuestionsFourOptions("Kotlin'de \"coroutine\"lerin kullanım amacı nedir?", arrayOf("Eşzamanlı programlama için kullanılır.",
                "Hata yakalama ve işleme için kullanılır.","Veritabanı işlemlerini kolaylaştırmak için kullanılır.",
            "Kullanıcı arayüzü bileşenleri oluşturmak için kullanılır."),0),
        QuestionsFourOptions("Kotlin'de, \"var\" ve \"val\" arasındaki fark nedir?", arrayOf("\"var\" değiştirilebilirken, \"val\" değiştirilemez.",
            "\"val\" değiştirilebilirken, \"var\" değiştirilemez.", "İkisi de aynı anlama gelir ve değiştirilebilir.",
            "İkisi de aynı anlama gelir ve değiştirilemez."),0),
        QuestionsFourOptions("Kotlin'de \"when\" ifadesi ne için kullanılır?", arrayOf("Döngülerde kullanılır.",
                "Koşullu ifadelerde kullanılır.", "Veri dönüşümlerinde kullanılır.","İstisna işlemlerinde kullanılır."),1),
        QuestionsFourOptions("Kotlin'de \"lateinit\" anahtar kelimesi ne anlama gelir?", arrayOf("Bir değişkenin null olabileceğini belirtir.",
            "Bir değişkenin geç başlatıldığını belirtir.", "Bir değişkenin değerinin değiştirilemeyeceğini belirtir.","Bir değişkenin hala tanımlanmadığını belirtir."),1),
    )
    fun getCurrentQuestions(): QuestionsFourOptions {
        return fourOptionsQuestions[currentQuestionIndex]
    }
    override fun getCurrentQuestionsIndex(): Int {
        return currentQuestionIndex
    }

    override fun moveToNextQuestion(): Boolean {
        currentQuestionIndex++
        return currentQuestionIndex < fourOptionsQuestions.size
    }
    fun checkAnswers(selectedOption: Int): Boolean {
        val currentQuestion = getCurrentQuestions()
       // val isAnswerCorrect = selectedOption == currentQuestion.correntAnswerIndex
        return if (selectedOption == currentQuestion.correctAnswerIndex) {
            score++
            true
        } else {
            falseAnswers++
            false
        }
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
        return fourOptionsQuestions.size
    }
    override fun getFalseAnswers(): Int {
        return falseAnswers
    }
    override fun calculateSuccessRate(score: Int, totalQuestions: Int): String {
        val successRate = (getScore().toFloat() / getTotalQuestions()) * 100
        return "%" + String.format("%.1f", successRate)
    }
}