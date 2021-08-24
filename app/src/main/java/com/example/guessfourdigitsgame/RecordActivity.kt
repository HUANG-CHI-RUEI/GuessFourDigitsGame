package com.example.guessfourdigitsgame

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.guessfourdigitsgame.adapters.RecordAdapter
import com.example.guessfourdigitsgame.database.DBRepository
import com.example.guessfourdigitsgame.database.ioThread
import com.example.guessfourdigitsgame.viewmodels.RecordViewModel
import kotlinx.android.synthetic.main.activity_record.*

class RecordActivity : AppCompatActivity() {

    private var repositoryv = DBRepository(this)

    private var recordSet = ArrayList<RecordViewModel>()

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
    }

    private fun processViews() {
        setSupportActionBar(RecordToolbar)

        guess_cnt = intent.getIntExtra("GuessCount", 0)

        AlertDialog.Builder(this)
            .setTitle(getString(R.string.record_dialog_title))
            .setMessage("$guess_cnt ${getString(R.string.record_dialog_times)}")
            .show()

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

        recordSet.add(
            RecordViewModel(
                rank = rank,
                count = count,
                datetime = datetime
            )
        )

        ioThread {
            val record = repositoryv.fetRankRecord()
            record.forEachIndexed { index, item ->
                recordSet.add(
                    RecordViewModel(
                        "${index + 1}",
                        item.count.toString(),
                        item.datetime
                    )
                )
            }
        }
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