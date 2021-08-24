package com.example.guessfourdigitsgame.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.guessfourdigitsgame.R
import com.example.guessfourdigitsgame.viewmodels.ResultViewModel
import kotlinx.android.synthetic.main.row_result_view.view.*


class ResultAdapter(private var dataSet: ArrayList<ResultViewModel>): RecyclerView.Adapter<ResultAdapter.ViewHolder>() {

    class ViewHolder(view:View): RecyclerView.ViewHolder(view) {
        val lblCount = view.lblCountRecord!!
        val lblGuess = view.lblGuess!!
        val lblResult = view.lblResult!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_result_view, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.lblCount.text = dataSet[position].count
        holder.lblGuess.text = dataSet[position].guess
        holder.lblResult.text = dataSet[position].result
    }

    override fun getItemCount() = dataSet.size

    fun add(result: ResultViewModel) {
        dataSet.add(result)
        notifyItemInserted(dataSet.size)
    }

    fun reset(count: String, guess: String, result: String) {
        dataSet = arrayListOf(
            ResultViewModel(
                count = count,
                guess = guess,
                result = result
            )
        )
        notifyDataSetChanged()
    }
}