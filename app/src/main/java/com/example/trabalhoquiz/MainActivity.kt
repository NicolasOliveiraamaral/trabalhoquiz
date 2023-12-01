package com.example.trabalhoquiz

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.trabalhoquiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val questions = arrayOf(
        "Qual é o elemento químico cujo símbolo é O?",
        "Quem é o autor(a) do livro Dom Casmurro?",
        "Qual é a capital do Canadá?",
        "Qual o maior campeão da copa do Brasil?",
        "Qual a linguagem de programação mais usada no mundo?",
        "Qual o país mais populoso do mundo?",
        "Onde está localizado a Torre Eiffel?",
        "Em que é especilizado um cardiologista?",
        "Qual o jogo mais jogado no mundo?",
        "Qual o sistema operacional usado nos celulares da LG, Motorola e Samsung?")

    private val options = arrayOf(arrayOf("Oxigênio","Ouro","Ozônio"),
        arrayOf( "Machado de Assis","Jorge Amado","Clarice Lispector"),
        arrayOf("Toronto","Vancouver","Ottawa"),
        arrayOf("Flamengo","Palmeiras","Cruzeiro"),
        arrayOf("Java", "Kotlin", "C++"),
        arrayOf("India","Inglaterra","Russia"),
        arrayOf("Espanha","França","Argentina"),
        arrayOf("Pulmão","Pele","Coração"),
        arrayOf("Lol","CS","Fifa"),
        arrayOf("IOS","Android","Windows Phone")
    )

    private val correctAnswers = arrayOf(0, 0, 2, 2, 0,0, 1, 2, 0,0)

    private var currentQuestionsIndex = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayQuestions()

        binding.option1Button.setOnClickListener{
            checkanswer(0)
        }
        binding.option2Button.setOnClickListener {
            checkanswer(1)
        }

        binding.option3Button.setOnClickListener {
            checkanswer(2)
        }
        binding.restartButton.setOnClickListener {
            restarQuiz()
        }
    }

    private fun correctButtonCollors(buttonIndex: Int){
        when(buttonIndex){
            0 ->    binding.option1Button.setBackgroundColor(Color.GREEN)
            1 ->    binding.option2Button.setBackgroundColor(Color.GREEN)
            2 ->    binding.option3Button.setBackgroundColor(Color.GREEN)
        }
    }
    private fun wrongButtonCollors(buttonIndex: Int){
        when(buttonIndex){
            0 -> binding.option1Button.setBackgroundColor(Color.RED)
            1 -> binding.option2Button.setBackgroundColor(Color.RED)
            2 -> binding.option3Button.setBackgroundColor(Color.RED)
        }
    }

    private fun resetButtonColors(){
        binding.option1Button.setBackgroundColor(Color.rgb(50,59,96))
        binding.option2Button.setBackgroundColor(Color.rgb(50,59,96))
        binding.option3Button.setBackgroundColor(Color.rgb(50,59,96))
    }
    private fun showResults(){
        Toast.makeText(this, "Sua pontuação: $score de ${questions.size}", Toast.LENGTH_LONG).show()
        binding.restartButton.isEnabled = true
    }
    private fun displayQuestions(){
        binding.question.text = questions[currentQuestionsIndex]
        binding.option1Button.text = options[currentQuestionsIndex][0]
        binding.option2Button.text = options[currentQuestionsIndex][1]
        binding.option3Button.text = options[currentQuestionsIndex][2]
        resetButtonColors()
    }
    private fun checkanswer(selectedAnswerIndex: Int){
        val correctAnswerIndex = correctAnswers[currentQuestionsIndex]

        if (selectedAnswerIndex == correctAnswerIndex){
            score++
            correctButtonCollors(selectedAnswerIndex)
        } else {
            wrongButtonCollors(selectedAnswerIndex)
            correctButtonCollors(correctAnswerIndex)
        }
        if (currentQuestionsIndex < questions.size - 1){
            currentQuestionsIndex++
            binding.question.postDelayed({displayQuestions()}, 1000)
        } else {
            showResults()
        }
    }
    private fun restarQuiz(){
        currentQuestionsIndex = 0
        score = 0
        displayQuestions()
        binding.restartButton.isEnabled = false
    }
}