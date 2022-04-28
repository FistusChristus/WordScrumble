package com.example.wordscramble

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.wordscramble.databinding.ActivityMainBinding
import kotlin.random.Random

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        startGame()
    }

    private fun startGame() {
        binding.editText.setText("")
        val word = getRandomWord()
        val mixedWord = word.toCharArray().let {
            it.shuffle()
            it.concatToString()
        }

        binding.cardView.text = mixedWord
        binding.succeedButton.setOnClickListener {
            val userWord = binding.editText.text.toString() ?: ""
            if (userWord == word) {
                Toast.makeText(this, "VICTORY", Toast.LENGTH_SHORT).show()
                startGame()
            } else {
                Toast.makeText(this, "Failure", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun getRandomWord(): String {
        val words = resources.getStringArray(R.array.words)
        return words[Random.nextInt(words.size)]
    }
}

