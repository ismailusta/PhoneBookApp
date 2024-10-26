package com.example.turkcelllab17

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class EklemeActivity : AppCompatActivity() {
    var user: User? = null
    var veriislem: VeriIslemleri

    init {
        veriislem = VeriIslemleri(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ekleme)

        // Pencere kenar boşluklarını ayarla
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Intent'ten kisi_id değerini al
        val kisi_id = intent.getIntExtra("kisi_id", -1)
        if (kisi_id == -1) {
            // Yeni bir kullanıcı ekleniyor
            user = User()
            findViewById<Button>(R.id.Delete).visibility = View.GONE
        } else {
            // Mevcut bir kullanıcı düzenleniyor
            user = veriislem.TekListe(kisi_id)
            // Eğer user null değilse, alanları doldur
            if (user != null) {
                findViewById<EditText>(R.id.editTextText).setText(user?.name)
                findViewById<EditText>(R.id.editTextTextEmailAddress).setText(user?.email)
                findViewById<EditText>(R.id.editTextTextPassword).setText(user?.password)
                findViewById<Button>(R.id.Delete).visibility = View.VISIBLE
            } else {
                // Kullanıcı bulunamazsa, silme butonunu gizle
                findViewById<Button>(R.id.Delete).visibility = View.GONE
            }
        }
    }

    // Kullanıcıyı sil
    fun delete(view: View) {
        user?.id?.let {
            veriislem.sil(it)
        }
        // İşlem sonrası aktiviteyi kapat
        setResult(RESULT_OK)
        finish()
    }

    // Kullanıcıyı kaydet
    fun save(view: View) {
        // EditText alanlarından verileri al ve user'a ata
        user?.name = findViewById<EditText>(R.id.editTextText).text.toString()
        user?.email = findViewById<EditText>(R.id.editTextTextEmailAddress).text.toString()
        user?.password = findViewById<EditText>(R.id.editTextTextPassword).text.toString()

        if (user?.id == null) {
            // Yeni kullanıcı ekle
            veriislem.Ekle(user!!)
        } else {
            // Mevcut kullanıcıyı güncelle
            veriislem.Guncelle(user!!)
        }

        // İşlem sonrası aktiviteyi kapat
        setResult(RESULT_OK)
        finish()
    }
}
