package com.example.guessfourdigitsgame.database

import android.content.Context

class DBRepository constructor(context: Context) {

    private fun getAllRecords() = recordDAO.getAll()

    // 資料庫物件
    private val db = RecordsDatabase.getDatabase(context)
    // 資料庫操作物件
    private val recordDAO = db.recordDAO()

    // 取得排名資料
    fun fetRankRecord() = recordDAO.getAllOrderByRank()

    // 新增遊戲紀錄
    fun insertRecord(record: DataModel) = recordDAO.insert(record)

    // 刪除所有紀錄
    fun deleteAllRecord() = recordDAO.deleteAll(getAllRecords())
}