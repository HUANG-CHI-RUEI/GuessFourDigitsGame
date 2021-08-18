package com.example.guessfourdigitsgame

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.guessfourdigitsgame.databinding.ActivityMaterialMainBinding
import kotlinx.android.synthetic.main.content_material_main.*

class MaterialMainActivity: AppCompatActivity() {
    private lateinit var binding: ActivityMaterialMainBinding

    private val secretNumber = SecretDigit()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMaterialMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        resetViews()
    }

    // Reset Game
    private fun resetViews() {
        edtInput.setText("")
        lblCount.text = getString(R.string.lbl_count)
        lblGuess.text = getString(R.string.lbl_guess)
        lblResult.text = getString(R.string.lbl_result)
        secretNumber.input = ""
        secretNumber.generateSecret()
    }

    fun validateInput(view: View) {
        var result = ""
        var title = ""

        secretNumber.input = edtInput.text.toString()

        result = secretNumber.validate()

        if (result.contains("4A")) {
            title = getString(R.string.title_bingo)
            result += "\n${secretNumber.answer}"
        }
        else {
            title = getString(R.string.title_try_again)
        }

        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(result)
            .show()

        lblCount.append("\n${secretNumber.guess_count}")
        lblGuess.append("\n${secretNumber.input}")
        lblResult.append("\n${result}")

    }


}