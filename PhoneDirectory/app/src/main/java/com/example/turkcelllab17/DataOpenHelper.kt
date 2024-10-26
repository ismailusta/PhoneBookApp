package com.example.turkcelllab17

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataOpenHelper(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(db: SQLiteDatabase?) {
        val sqlSorgu = "CREATE TABLE User (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name VARCHAR, email VARCHAR, password VARCHAR)"
        db!!.execSQL(sqlSorgu)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}