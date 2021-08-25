package com.example.guessfourdigitsgame

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.guessfourdigitsgame.adapters.ResultAdapter
import com.example.guessfourdigitsgame.database.DBRepository
import com.example.guessfourdigitsgame.database.DataModel
import com.example.guessfourdigitsgame.database.ioThread
import com.example.guessfourdigitsgame.databinding.ActivityMaterialMainBinding
import com.example.guessfourdigitsgame.viewmodels.ResultViewModel
import kotlinx.android.synthetic.main.content_material_main.*
import java.text.SimpleDateFormat
import java.util.*

class MaterialMainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMaterialMainBinding

    val secretNumber = SecretDigit()

    private var dataset = arrayListOf<ResultViewModel>()

    private lateinit var repository: DBRepository

    private lateinit var dataAdapter: ResultAdapter

    private val dataFormat =
        SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())

    private val TAG = MaterialMainActivity::class.java.simpleName

    private var count = ""      // User Guess Count
    private var guess = ""      // User Input
    private var result = ""     // SecretNumber Output
    private var now = ""        // Now string

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMaterialMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        repository = DBRepository(this)

        initialData()

        processView()

        resetViews()

        /* 重玩 */
        binding.fab.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle(getString(R.string.title_replay))
                .setMessage(getString(R.string.msg_replay))
                .setPositiveButton(getString(R.string.dialog_btn_yes)) { _, _ ->
                    resetViews()
                    Toast.makeText(this, getString(R.string.toast_msg_replay), Toast.LENGTH_SHORT)
                        .show()
                }
                .setNegativeButton(getString(R.string.dialog_btn_no), null)
                .show()
        }
        Log.d(TAG, "onCreate: ")
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
                result = "${getString(R.string.msg_excellent)}  ${ secretNumber.answer }"
            } else {
                title = getString(R.string.title_bingo)
                result += "\n${secretNumber.answer}"
            }

        } else {
            title = getString(R.string.title_try_again)
        }

        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(result)
            .let {
                if (title.contains(getString(R.string.title_bingo)) ||
                    title.contains(getString(R.string.title_excellent))
                ) {
                    it.setPositiveButton(getString(R.string.btn_ok)) { _, _ ->
                        edtInput.isEnabled = false
                        now = dataFormat.format(Calendar.getInstance().time)
                        val record = DataModel(secretNumber.guess_count, now)
                        ioThread { repository.insertRecord(record) }

                        val intent = Intent(this, RecordActivity::class.java)
                        intent.putExtra("GuessCount", secretNumber.guess_count)
                        startActivity(intent)
                    }.show()
                } else {
                    it.show()
                }
            }


        result = if (title.contains(getString(R.string.title_excellent)))
            "4A0B"
        else
            result

        // 視覺處理 新增一筆猜測紀錄
        dataAdapter.add(
            ResultViewModel(
                count = secretNumber.guess_count.toString(),
                guess = secretNumber.input, result = result
            )
        )
        // 每次猜完清空欄位
        edtInput.setText("")
    }

    private fun processView() {
        recycleResult.apply {
            layoutManager = LinearLayoutManager(this.context)
            setHasFixedSize(true)
            addItemDecoration(
                DividerItemDecoration(
                    this.context,
                    DividerItemDecoration.VERTICAL
                )
            )
            dataAdapter = ResultAdapter(dataset)
            adapter = dataAdapter
        }
    }

    private fun initialData() {
        count = getString(R.string.lbl_count)
        guess = getString(R.string.lbl_guess)
        result = getString(R.string.lbl_result)

        dataset.add(
            ResultViewModel(
                count = count,
                guess = guess,
                result = result
            )
        )
    }

    // <summary> 重設畫面元件，遊戲資料 </summary>
    private fun resetViews() {
        edtInput.setText("")
        edtInput.isEnabled = true
        dataAdapter.reset(count, guess, result)
        secretNumber.input = ""
        secretNumber.guess_count = 0
        secretNumber.generateSecret()
    }

}