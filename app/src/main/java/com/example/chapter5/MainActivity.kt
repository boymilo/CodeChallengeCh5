package com.example.chapter5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    val parent by lazy { findViewById<LinearLayout>(R.id.main) }
    val ivMenu1 by lazy { findViewById<ImageView>(R.id.ivMenu1) }
    val ivMenu2 by lazy { findViewById<ImageView>(R.id.ivMenu2) }
    val tvMenu1 by lazy { findViewById<TextView>(R.id.tvMenu1) }
    val tvMenu2 by lazy { findViewById<TextView>(R.id.tvMenu2) }
    var name: String =""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        name = intent.extras?.getString("Name").toString()
        tvMenu1.text = "$name vs Pemain"
        tvMenu2.text = "$name vs CPU"

        ivMenu1.setOnClickListener {
            val intent1 = Intent(this, VsPlayer::class.java)
            intent1.putExtra("Name", name)
            startActivity(intent1)
            finish()
        }

        ivMenu2.setOnClickListener {
            val intent1 = Intent(this, VsCpu::class.java)
            intent1.putExtra("Name", name)
            startActivity(intent1)
            finish()
        }

    }

    override fun onResume() {
        super.onResume()
        val snackbar = Snackbar.make(parent, "Selamat Datang $name", Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction("Tutup") {
            snackbar.dismiss()
        }.show()
    }
}
