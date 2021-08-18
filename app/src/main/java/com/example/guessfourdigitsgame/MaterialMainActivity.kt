package com.example.guessfourdigitsgame

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.guessfourdigitsgame.databinding.ActivityMaterialMainBinding
import kotlinx.android.synthetic.main.content_material_main.*

class MaterialMainActivity: AppCompatActivity() {
    private lateinit var binding: ActivityMaterialMainBinding

    private val secretNumber = SecretDigit()

    private var dataset = arrayListOf<ItemModel>()

    private lateinit var dataAdapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMaterialMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        /* Initial First Row Data */
        dataset.add(ItemModel(count = "Count", guess = "Guess", result = "Result"))
        dataAdapter = ItemAdapter(dataset)

        recycleResult.layoutManager = LinearLayoutManager(this)
        recycleResult.setHasFixedSize(false)

        recycleResult.adapter = dataAdapter

        resetViews()

        /* 重玩 */
        binding.fab.setOnClickListener{
            AlertDialog.Builder(this)
                .setTitle(getString(R.string.title_replay))
                .setMessage(getString(R.string.msg_replay))
                .setPositiveButton(getString(R.string.dialog_btn_yes)) {
                        _, _ ->
                    resetViews()
                    Toast.makeText(this, getString(R.string.toast_msg), Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton(getString(R.string.dialog_btn_no), null)
                .show()


        }
    }

    /* Reset Game */
    private fun resetViews() {
        edtInput.setText("")
        dataAdapter.reset()
        secretNumber.input = ""
        secretNumber.guess_count = 0
        secretNumber.generateSecret()
    }

    fun validateInput(view: View) {
        var result: String


        /* 取得使用者輸入 */
        secretNumber.input = edtInput.text.toString()
        /* 驗證使用者輸入 */
        result = secretNumber.validate()

        if (result.contains("4A")) {
            if (secretNumber.guess_count < 3) {
                title = getString(R.string.title_excellent)
                result = getString(R.string.msg_excellent) + secretNumber.answer
            } else {
                title = getString(R.string.title_bingo)
                result += "\n${secretNumber.answer}"
            }

        }
        else {
            title = getString(R.string.title_try_again)
        }

        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(result)
            .show()

        result = if(title.contains(getString(R.string.title_excellent)))
            "4A0B"
        else
            result

        // 視覺處理 新增一筆猜測紀錄
        dataAdapter.add(ItemModel(count = secretNumber.guess_count.toString(),
        guess = secretNumber.input, result = result))

        // 每次猜完清空欄位
        edtInput.setText("")

    }


}