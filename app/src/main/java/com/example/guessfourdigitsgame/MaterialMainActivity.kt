package com.example.guessfourdigitsgame

import android.os.Bundle
import android.widget.Toast
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

        // 重玩
        binding.fab.setOnClickListener{ view ->
            AlertDialog.Builder(this)
                .setTitle(getString(R.string.title_replay))
                .setMessage(getString(R.string.msg_replay))
                .setPositiveButton(getString(R.string.dialog_btn_yes)) {
                        dialog, _ ->
                    resetViews()
                    Toast.makeText(this, getString(R.string.toast_msg), Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton(getString(R.string.dialog_btn_no), null)
                .show()


        }
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

    fun validateInput() {
        val title: String

        secretNumber.input = edtInput.text.toString()

        var result: String = secretNumber.validate()

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