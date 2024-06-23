package ru.netology.millionaire

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import ru.netology.millionaire.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val rounds = mutableListOf<Round>()
    private var currentRound = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvQuestion.text = "Тут будет первый вопрос"
        fillRounds()
        updateUI()
        binding.button1.setOnClickListener{
            processRound(1)
        }
        binding.button2.setOnClickListener{
            processRound(2)
        }
        binding.button3.setOnClickListener{
            processRound(3)
        }
        binding.button4.setOnClickListener{
            processRound(4)
        }
    }
    private fun fillRounds(){
        rounds.run{
            add(Round("Что такое луна?","Звезда","Планета", "Спутник", "Круг сыра",3,100))
            add(Round("В каком году запущен первый спутник?","1957","1961", "1965", "1969",1,1000))
            add(Round("Сколько спутников у Марса?","0","1", "2", "4",3,10000))
            add(Round("Как называется спутник Плутона?","Нет спутников","Харон", "Энцелад", "Ио",2,100000))
            add(Round("Какой крупнейщий спутник у Юпитера?","Европа","Каллисто", "Титан", "Ганимед",4,1000000))
        }
    }
    private fun updateUI(){
        binding.tvQuestion.text = rounds[currentRound].question
        binding.tvValue.text = rounds[currentRound].value.toString()
        binding.button1.text = rounds[currentRound].answer1
        binding.button2.text = rounds[currentRound].answer2
        binding.button3.text = rounds[currentRound].answer3
        binding.button4.text = rounds[currentRound].answer4
    }
    private fun checkAnswer(givenId: Int) = (givenId == rounds[currentRound].rightId)
    private fun goNextRound(): Boolean{
        if(currentRound>=rounds.size - 1) return false
        currentRound++
        updateUI()
        return true
    }
    private fun processRound(givenId: Int){
        if(checkAnswer(givenId)){
            if(!goNextRound()){
                Toast.makeText(this, "YOU WIN :)", Toast.LENGTH_SHORT).show()
                finish()
            }
        } else{
            Toast.makeText(this, "YOU LOOSE :(", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}