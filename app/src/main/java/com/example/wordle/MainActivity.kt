package com.example.wordle

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.wordle.FourLetterWordList.getRandomFourLetterWord

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonClick = findViewById<Button>(R.id.guessButton)
        var userGuess = findViewById<EditText>(R.id.editText)

        var g1 = findViewById<TextView>(R.id.guess1)
        var g2 = findViewById<TextView>(R.id.guess2)
        var g3 = findViewById<TextView>(R.id.guess3)

        var x1 = findViewById<TextView>(R.id.userGuess1)
        var x2 = findViewById<TextView>(R.id.userGuess2)
        var x3 = findViewById<TextView>(R.id.userGuess3)

        var c1 = findViewById<TextView>(R.id.guess1Check)
        var c2 = findViewById<TextView>(R.id.guess2Check)
        var c3 = findViewById<TextView>(R.id.guess3Check)

        var m1 = findViewById<TextView>(R.id.match1)
        var m2 = findViewById<TextView>(R.id.match2)
        var m3 = findViewById<TextView>(R.id.match3)

        var guesses = 0
        val answer = findViewById<TextView>(R.id.correctWord)
        buttonClick.setOnClickListener {
            // Getting the user input
            var guess = ""

            guess = userGuess.text.toString()

            // Showing the user input
            Toast.makeText(this, checkGuess(guess), Toast.LENGTH_SHORT).show()

            if (guesses > 2){
                answer.visibility = View.VISIBLE
                answer.text = wordToGuess
                userGuess.isEnabled = false
                buttonClick.isEnabled = false
            }
            guesses +=1
        }

    }

    private var wordToGuess = getRandomFourLetterWord()

    private fun checkGuess(guess: String): String {
        var result = ""
        for (i in 0..3) {
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            } else if (guess[i] in wordToGuess) {
                result += "+"
            } else {
                result += "X"
            }
        }
        return result
    }
}