package com.example.guessfourdigitsgame

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.record_description.view.*

class ItemAdapter(private var dataset: ArrayList<ItemModel>):
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    // Initial View Component
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val lbCount = view.lblCountRecord
        val lbGuess = view.lblGuess
        val lbResult = view.lblResult
    }

    // Display data on view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout  = LayoutInflater.from(parent.context)
            .inflate(R.layout.record_description, parent, false)
        return ViewHolder(layout)
    }

    // <summary> Replace the content of a view </summary>
    // <param name="holder"> </param>
    // <param name="position"> Data Index </param>
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.lbCount.text = dataset[position].count
        holder.lbGuess.text = dataset[position].guess
        holder.lbResult.text = dataset[position].result
    }

    override fun getItemCount() = dataset.size

    fun add(item: ItemModel) {
        dataset.add(item)
        notifyItemInserted(dataset.size)
    }

    fun reset() {
        dataset  = arrayListOf(
            ItemModel(
                count = "Count",
                guess = "Guess",
                result = "Result"
            ))
        notifyDataSetChanged()
    }

}
