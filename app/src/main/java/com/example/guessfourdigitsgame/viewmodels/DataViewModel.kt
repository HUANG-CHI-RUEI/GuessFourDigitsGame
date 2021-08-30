package com.example.guessfourdigitsgame.viewmodels

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


object DataViewModel {
    @Parcelize
    data class ResultViewModel(var count: String, var guess: String, var result: String) : Parcelable

    data class RecordViewModel(var rank:String, var count:String, var datetime:String)

    fun initialResultViewModel(count: String, guess: String, result: String)
        = ResultViewModel(count, guess, result)

    fun initialRecordViewModel(rank: String, count: String, datetime: String)
        = RecordViewModel(rank, count, datetime)
}


