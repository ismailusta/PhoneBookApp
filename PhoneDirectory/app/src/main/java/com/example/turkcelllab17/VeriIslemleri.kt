package com.example.turkcelllab17

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class VeriIslemleri(context: Context) {
    var dataOpenHelp :DataOpenHelper? = null
    var db : SQLiteDatabase? = null

    init {
        dataOpenHelp = DataOpenHelper(context,"User",null,1)
    }
    fun Ac()
    {
        db = dataOpenHelp?.writableDatabase
    }
    fun Kapat()
    {
        if(db != null && db?.isOpen() == true){
            db?.close()
        }
    }
    fun Ekle(user : User){
        val cv = ContentValues()
        cv.put("name",user.name)
        cv.put("email",user.email)
        cv.put("password",user.password)
        Ac()
        db?.insert("User",null,cv)
        Kapat()
    }
    fun Guncelle(user : User) {
        val cv = ContentValues()
        cv.put("name", user.name)
        cv.put("email", user.email)
        Ac()
        db?.update("User",cv,"id= ?", arrayOf(user.id.toString()))
        Kapat()
    }
    fun sil(id : Int){
        Ac()
        db?.delete("User","id= ?", arrayOf(id.toString()))
        Kapat()
    }
    fun TumListe() : ArrayList<User>{
        val liste = ArrayList<User>()
        Ac()
        val sorgu = "Select * from User order by name desc"
        val cursor = db?.rawQuery(sorgu,null)
        if (cursor!!.moveToFirst()){
            do {
                val user = User()
                user.id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                user.name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
                user.email = cursor.getString(cursor.getColumnIndexOrThrow("email"))
                user.password = cursor.getString(cursor.getColumnIndexOrThrow("password"))
                liste.add(user)
            }
                while (cursor.moveToNext())
        }
        cursor.close()
        return liste
        Kapat()
    }
    fun TekListe(id : Int) : User?
    {
        var user: User? = null // Başlangıçta null
        Ac()
        val sorgu = "Select * from User where id = ?"
        val cursor = db?.rawQuery(sorgu, arrayOf(id.toString()))

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                // User nesnesini burada oluştur ve değerleri ata
                user = User().apply {
                    this.id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                    this.name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
                    this.email = cursor.getString(cursor.getColumnIndexOrThrow("email"))
                    this.password = cursor.getString(cursor.getColumnIndexOrThrow("password"))
                }
            }
            cursor.close()
        }
        return user
    }
}