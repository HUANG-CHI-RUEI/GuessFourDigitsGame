package com.example.guessfourdigitsgame

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.guessfourdigitsgame.adapters.RecordAdapter
import com.example.guessfourdigitsgame.database.DBRepository
import com.example.guessfourdigitsgame.database.ioThread
import com.example.guessfourdigitsgame.viewmodels.DataViewModel
import kotlinx.android.synthetic.main.activity_record.*

class RecordActivity : AppCompatActivity() {

    private var repository = DBRepository(this)
    private var recordSet = arrayListOf<DataViewModel.RecordViewModel>()
    var adapter = RecordAdapter(recordSet)

    // Variable
    private var guess_cnt = 0       // User Guess Times
    private var rank = ""           // User Rank
    private var count = ""          // User Record Times
    private var datetime = ""       // User play Datetime



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)

        // 變數初始化
        initialData()
        // 視覺元件初始化
        processViews()

        // 刪除紀錄
        fabDelete.setOnClickListener{
            AlertDialog.Builder(this)
                .setTitle(getString(R.string.dialog_title_delete))
                .setMessage(getString(R.string.dialog_msg_delete))
                .setPositiveButton(getString(R.string.dialog_btn_yes)) {
                    _, _ ->
                    ioThread { repository.deleteAllRecord() }
                    reset()
                    Toast.makeText(this, "Record delete finished", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton(getString(R.string.dialog_btn_no), null)
                .show()
        }
    }

    private fun processViews() {
        setSupportActionBar(RecordToolbar)

        val entry = intent.getIntExtra("ActivityEntry", -1)
        guess_cnt = intent.getIntExtra("GuessCount", 0)

        // 如果從遊戲換面進入
        if (entry == 1) {
            AlertDialog.Builder(this)
                .setTitle(getString(R.string.record_dialog_title))
                .setMessage("$guess_cnt ${getString(R.string.record_dialog_times)}")
                .show()
        }


        rycRecord.apply {
            // Set Recycler View Layout
            layoutManager = LinearLayoutManager(this.context)
            setHasFixedSize(true)
            // Set Recycler View Divider
            addItemDecoration(
                DividerItemDecoration(
                    this.context,
                    DividerItemDecoration.VERTICAL
                )
            )
            // Set Recycler View Adapter
            adapter = RecordAdapter(recordSet)
            adapter = adapter
        }
    }

    private fun initialData() {
        rank = getString(R.string.lbl_rank)
        count = getString(R.string.lbl_count)
        datetime = getString(R.string.lbl_datetime)

        recordSet.add(DataViewModel.initialRecordViewModel(rank, count, datetime))

    }

    // <summary> 重設排名資料 </summary>
    private fun reset() {
        adapter.reset(rank = rank, count = count, datetime = datetime)
        rycRecord.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            finish()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

}