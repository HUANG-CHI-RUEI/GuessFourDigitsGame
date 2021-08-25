package com.example.guessfourdigitsgame.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.guessfourdigitsgame.R
import com.example.guessfourdigitsgame.viewmodels.RecordViewModel
import kotlinx.android.synthetic.main.row_record_view.view.*

class RecordAdapter(private var dataSet: ArrayList<RecordViewModel>) : RecyclerView.Adapter<RecordAdapter.ViewHolder>(){

    class ViewHolder(view:View): RecyclerView.ViewHolder(view) {
        val lblRank = view.lblRankRecord!!
        val lblCount = view.lblCountRecord!!
        val lblDate = view.lblDatetimeRecord!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_record_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.lblRank.text = dataSet[position].rank.toString()
        holder.lblCount.text = dataSet[position].count
        holder.lblDate.text = dataSet[position].datetime.toString()
    }

    override fun getItemCount() = dataSet.size

    fun reset(rank: String, count: String, datetime: String) {
        Log.d(RecordAdapter::class.java.simpleName, "reset: ")

        dataSet = arrayListOf(
            RecordViewModel(
                rank = rank,
                count = count,
                datetime = datetime
            )
        )
        notifyDataSetChanged()
    }
}