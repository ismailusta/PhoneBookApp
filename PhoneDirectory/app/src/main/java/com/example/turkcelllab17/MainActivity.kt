package com.example.turkcelllab17

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    var liste = ArrayList<User>()
    var veriIslemleri :VeriIslemleri
    init {
        veriIslemleri = VeriIslemleri(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        liste = veriIslemleri.TumListe()
        val adapter = AdapterUser(liste, this::ListeElemaninaClick )
        val layoutMan = LinearLayoutManager(this)
        layoutMan.orientation = LinearLayoutManager.VERTICAL
        val lsview = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.lsview)
        lsview.layoutManager = layoutMan
        lsview.addItemDecoration(DividerItemDecoration(this,layoutMan.orientation))
        lsview.adapter = adapter
    }

    fun ItemEkle(view: View) {
        val i = Intent(this, EklemeActivity::class.java)
        startActivityForResult(i,1)
    }
    fun ListeElemaninaClick(position:Int){
         val i = Intent(this, EklemeActivity::class.java)
        i.putExtra("kisi_id",liste.get(position).id)
        startActivityForResult(i,2)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK){
            liste.clear()
            liste.addAll(veriIslemleri.TumListe())
            findViewById<RecyclerView>(R.id.lsview).adapter!!.notifyDataSetChanged()
        }
    }
}