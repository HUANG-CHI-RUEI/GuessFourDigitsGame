package com.example.guessfourdigitsgame.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


//  資料類別：存入資料庫的紀錄
//  @Entity：宣告為 Room Entity 物件，tableName 屬性：指定資料表的名稱
// Data Identification
// Guess Count for rank
// Play Datetime
@Entity(tableName = "Game_Record")
data class DataModel (
    @ColumnInfo(name="count") var count: Int,
    @ColumnInfo(name="datetime") var datetime: String
        ) {
    // @PrimaryKey：定義 PK，自動產生 Id，遞增
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    var id: Int = 0
}