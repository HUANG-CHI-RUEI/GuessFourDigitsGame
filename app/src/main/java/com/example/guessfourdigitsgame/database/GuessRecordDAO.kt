package com.example.guessfourdigitsgame.database

import androidx.room.*

@Dao
interface GuessRecordDAO {

    @Query("SELECT * FROM Game_Record")
    fun getAll(): List<DataModel>

    @Query("SELECT * FROM Game_Record ORDER BY count")
    fun getAllOrderByRank(): List<DataModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg  record: DataModel)

    @Delete
    fun deleteAll(record: ArrayList<DataModel>)
}