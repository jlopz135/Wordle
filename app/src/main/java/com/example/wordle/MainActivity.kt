package com.example.wordle

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.wordle.FourLetterWordList.getRandomFourLetterWord

var guesses = 0

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val reset = findViewById<Button>(R.id.resetButton)

        val starLeft = findViewById<ImageButton>(R.id.starLeft)
        val starRight = findViewById<ImageButton>(R.id.starRight)

        val buttonClick = findViewById<Button>(R.id.guessButton)
        val userGuess = findViewById<EditText>(R.id.editText)

        val g2 = findViewById<TextView>(R.id.guess2)
        val g3 = findViewById<TextView>(R.id.guess3)

        val x1 = findViewById<TextView>(R.id.userGuess1)
        val x2 = findViewById<TextView>(R.id.userGuess2)
        val x3 = findViewById<TextView>(R.id.userGuess3)

        val c1 = findViewById<TextView>(R.id.guess1Check)
        val c2 = findViewById<TextView>(R.id.guess2Check)
        val c3 = findViewById<TextView>(R.id.guess3Check)

        val m1 = findViewById<TextView>(R.id.match1)
        val m2 = findViewById<TextView>(R.id.match2)
        val m3 = findViewById<TextView>(R.id.match3)

        val answer = findViewById<TextView>(R.id.correctWord)
        reset.setOnClickListener {
            // lower button and change guess # visibility
            guesses = 0
            wordToGuess = getRandomFourLetterWord()
            starLeft.visibility = View.INVISIBLE
            starRight.visibility = View.INVISIBLE

            m1.visibility = View.INVISIBLE
            x1.visibility = View.INVISIBLE
            c1.visibility = View.INVISIBLE

            g2.visibility = View.INVISIBLE
            m2.visibility = View.INVISIBLE
            x2.visibility = View.INVISIBLE
            c2.visibility = View.INVISIBLE

            g3.visibility = View.INVISIBLE
            m3.visibility = View.INVISIBLE
            x3.visibility = View.INVISIBLE
            c3.visibility = View.INVISIBLE

            answer.visibility = View.INVISIBLE
            userGuess.hint = "Enter 4 Letter Guess Here"
            userGuess.isEnabled = true
            buttonClick.isEnabled = true
        }

        buttonClick.setOnClickListener {
            try {
                userGuess.hideKeyboard()
                // Getting the user input
                val guess = userGuess.text.toString()

                // Showing the user input
                val color = rightWrong(guess)
                when (guesses) {
                    0 -> {

                        x1.text = guess
                        m1.text = checkGuess(guess)
                        m1.visibility = View.VISIBLE
                        x1.visibility = View.VISIBLE
                        c1.visibility = View.VISIBLE


                    }
                    1 -> {
                        x2.text = guess
                        m2.text = checkGuess(guess)
                        g2.visibility = View.VISIBLE
                        x2.visibility = View.VISIBLE
                        c2.visibility = View.VISIBLE
                        m2.visibility = View.VISIBLE
                    }
                    2 -> {
                        x3.text = guess
                        m3.text = checkGuess(guess)
                        g3.visibility = View.VISIBLE
                        x3.visibility = View.VISIBLE
                        c3.visibility = View.VISIBLE
                        m3.visibility = View.VISIBLE
                    }

                    else -> {
                        println("Error")
                    }

                }
                guesses += 1
                if (guesses > 2) {
                    if (color) {
                        answer.setTextColor(Color.parseColor("#00FF00"))
                        starLeft.visibility = View.VISIBLE
                        starRight.visibility = View.VISIBLE

                    } else {
                        answer.setTextColor(Color.parseColor("#FF0000"))
                    }
                    answer.visibility = View.VISIBLE
                    answer.text = wordToGuess
                    userGuess.isEnabled = false
                    buttonClick.isEnabled = false
                }
            } catch (e: java.lang.StringIndexOutOfBoundsException) {
                Toast.makeText(this, "Need 4 Letters Per Guess", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    private fun rightWrong(guess: String): Boolean {
        var rW = false
        if (guess == wordToGuess) {
            rW = true
        }
        return rW
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