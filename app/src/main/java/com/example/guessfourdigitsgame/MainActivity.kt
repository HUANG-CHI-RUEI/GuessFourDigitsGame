package com.example.guessfourdigitsgame

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AlertDialog
import com.example.guessfourdigitsgame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding

    private lateinit var secretDigit: SecretDigit

    private var secret = ""

    private val PASS = 0

    private val NOT_PASS = 1

    private val NOT_VALID = 2

    private val REFRESH = 3

    private lateinit var myActivityLuncher: ActivityResultLauncher<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        secretDigit = SecretDigit()
        secret = secretDigit.getSecret()

        var msg: String
//        myActivityLuncher = registerForActivityResult(MainActivityResultContract()) {
//            refresh()
//        }
        viewBinding.buttonGuess.setOnClickListener {
            var type:Int
            hideKeyBoard()

            if(viewBinding.guessDigit.text.toString() != "") {
                val guess = viewBinding.guessDigit.text.toString()
                val guessResult = secretDigit.validate(guess)

                if(guessResult == "4A0B") {
                    msg = "Bingo"
                    type = PASS
                } else if (guessResult == "3A1B"){
                    msg = "3A1B"
                    type = NOT_PASS
                } else if (guessResult == "2A2B") {
                    msg = "2A2B"
                    type = NOT_PASS
                } else if (guessResult == "1A3B"){
                    msg = "1A3B"
                    type = NOT_PASS
                } else if (guessResult == "0A4B") {
                    msg = "0A4B"
                    type = NOT_PASS
                } else if (guessResult =="0A3B") {
                    msg = "0A3B"
                    type = NOT_PASS
                } else if (guessResult =="0A2B") {
                    msg = "0A2B"
                    type = NOT_PASS
                } else if (guessResult =="0A1B") {
                    msg = "0A1B"
                    type = NOT_PASS
                } else{
                    msg = "0A0B"
                    type = NOT_PASS
                }
                showDialogResult(type, msg)
            } else {
                msg = "Please enter 4 digits"
                type = NOT_VALID
                showDialogResult(type, msg)
            }

        }

    }

    private fun showDialogResult(type: Int, msg: String) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(msg)
        builder.setPositiveButton("OK"
        ) { dialog, _ ->
            when (type) {
                PASS -> {
                    myActivityLuncher.launch(secretDigit.getCount())
                }

                NOT_PASS -> {
                    viewBinding.guessDigit.setText("")
                    dialog?.dismiss()
                }

                NOT_VALID -> {
                    viewBinding.guessDigit.setText("")
                    dialog?.cancel()
                }

                REFRESH -> {
                    viewBinding.guessDigit.setText("")
                    refresh()
                    dialog?.cancel()
                }
            }
        }
        builder.show()
    }

    private fun refresh() {
        secretDigit = SecretDigit()
        secret = secretDigit.getSecret()

    }

    private fun hideKeyBoard() {
        val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
    }
}

