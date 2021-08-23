package com.example.guessfourdigitsgame.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [DataModel::class], version = 1)

abstract class RecordsDatabase: RoomDatabase(){
    abstract fun recordDAO(): GuessRecordDAO

    companion object{
        private var db: RecordsDatabase ?= null

        fun getDatabase(context: Context): RecordsDatabase =
            db ?: synchronized(this) {
                db ?: buildDatabase(context).also {db = it}
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, RecordsDatabase::class.java, "guess_record_database")
                .build()
    }
}