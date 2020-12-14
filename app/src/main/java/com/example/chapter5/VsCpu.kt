package com.example.chapter5

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide

class VsCpu : AppCompatActivity() {

    val idJudul by lazy { this.findViewById<ImageView>(R.id.ivJudul) }
    val batu by lazy { this.findViewById<ImageView>(R.id.ivBatu) }
    val batuCom by lazy { this.findViewById<ImageView>(R.id.ivBatuCom) }
    val kertas by lazy { this.findViewById<ImageView>(R.id.ivKertas) }
    val kertasCom by lazy { this.findViewById<ImageView>(R.id.ivKertasCom) }
    val gunting by lazy { this.findViewById<ImageView>(R.id.ivGunting) }
    val guntingCom by lazy { this.findViewById<ImageView>(R.id.ivGuntingCom) }
    val close by lazy {this.findViewById<ImageView>(R.id.ivClose)}
    val reset by lazy {this.findViewById<ImageView>(R.id.ivNewGame)}
    var name = ""
    val controller = Controller()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vs_cpu)
        Glide.with(this)
            .load("https://i.ibb.co/HC5ZPgD/splash-screen1.png")
            .into(idJudul)
        val tvPlayer by lazy { this.findViewById<TextView>(R.id.tvPlayer) }
        name = intent.extras?.getString("Name").toString()
        tvPlayer.text = name

        var pemain1 = mutableListOf(batu, gunting, kertas)
        var pilihan = mutableListOf("batu", "gunting", "kertas")

        pemain1.forEach {
            it.setOnClickListener {
                it.setBackgroundResource(R.drawable.ic_backgroud_clik)
                batu.isClickable = false
                gunting.isClickable = false
                kertas.isClickable = false
                Toast.makeText(this, "${tvPlayer.text} memilih ${pilihan[pemain1.indexOf(it)]}", Toast.LENGTH_SHORT).show()
                Log.e("MainActivity", "${tvPlayer.text} memilih ${pilihan[pemain1.indexOf(it)]}")
                pemain1.forEach { it.isClickable = false }
                controller.setPlayer1(pilihan[pemain1.indexOf(it)])
                acak()
                result()
            }
        }

        reset.setOnClickListener {
            reset()
        }

        close.setOnClickListener {
            menu()
        }


    }
    fun result(){
        val dialogBuilder = AlertDialog.Builder(this)
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_view, null, false)
        val goMenu by lazy { view.findViewById<Button>(R.id.btn2) }
        val pemenang by lazy{view.findViewById<TextView>(R.id.result)}
        val reset by lazy{view.findViewById<Button>(R.id.btn1)}
        dialogBuilder.setView(view)
        val dialog = dialogBuilder.create()
        dialog.setCancelable(false)
        dialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        when (controller.logicGame()){
            1 ->{
                pemenang.text = "${name}\nMENANG!"
            }
            2 ->{
                pemenang.text = "CPU\nMENANG!"
            }
        }
        goMenu.setOnClickListener {
            dialog.dismiss()
            menu()
        }
        reset.setOnClickListener {
            dialog.dismiss()
            reset()
        }
        dialog.show()

    }
    fun reset(){
        var pemain1 = mutableListOf(batu, gunting, kertas)
        var pemain2 = mutableListOf(batuCom, guntingCom, kertasCom)

        pemain1.forEach {
            it.setBackgroundResource(R.drawable.ic_background)
            it.isClickable = true
        }

        pemain2.forEach {
            it.setBackgroundResource(R.drawable.ic_background)
            it.isClickable = true
        }
    }
    fun menu(){
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("Name", name)
        startActivity(intent)
        finish()
    }

    fun acak(){
        var pemain2 = mutableListOf(batuCom, guntingCom, kertasCom)
        var pilihan = mutableListOf("batu", "gunting", "kertas")
        val result = pemain2.random()
        result.setBackgroundResource(R.drawable.ic_backgroud_clik)
        pemain2.forEach { it.isClickable = false }
        Toast.makeText(this, "CPU Memilih ${pilihan[pemain2.indexOf(result)]}", Toast.LENGTH_SHORT).show()
        controller.setPlayer2(pilihan[pemain2.indexOf(result)])
    }
}