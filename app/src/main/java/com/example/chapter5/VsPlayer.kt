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

class VsPlayer : AppCompatActivity() {

    var name = ""
    val controller = Controller()
    var readyPlayer1 = false
    var readyPlayer2 = false

    val idJudul by lazy {this.findViewById<ImageView>(R.id.ivJudul)}
    val batu by lazy {this.findViewById<ImageView>(R.id.ivBatu)}
    val batuCom by lazy {this.findViewById<ImageView>(R.id.ivBatuCom)}
    val kertas by lazy {this.findViewById<ImageView>(R.id.ivKertas)}
    val kertasCom by lazy {this.findViewById<ImageView>(R.id.ivKertasCom)}
    val gunting by lazy {this.findViewById<ImageView>(R.id.ivGunting)}
    val guntingCom by lazy {this.findViewById<ImageView>(R.id.ivGuntingCom)}
    val reset by lazy {this.findViewById<ImageView>(R.id.ivNewGame)}
    val close by lazy { this.findViewById<ImageView>(R.id.ivClose)}
    val tvPlayer by lazy{this.findViewById<TextView>(R.id.tvPlayer)}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vs_player)
        Glide.with(this)
            .load("https://i.ibb.co/HC5ZPgD/splash-screen1.png")
            .into(idJudul)
        name = intent.extras?.getString("Name").toString()
        tvPlayer.text = name
        var pemain1 = mutableListOf(batu, gunting, kertas)
        var pemain2 = mutableListOf(batuCom, guntingCom, kertasCom)
        var pilihan = mutableListOf("batu", "gunting", "kertas")
        pemain1.forEach {
            it.setOnClickListener {
                it.setBackgroundResource(R.drawable.ic_backgroud_clik)
                controller.setPlayer1(pilihan[pemain1.indexOf(it)])
                Toast.makeText(this, "${tvPlayer.text} Memilih ${controller.getPlayer1()}", Toast.LENGTH_SHORT).show()
                Log.e("MainActivity", "${tvPlayer.text} Memilih ${controller.getPlayer1()}")
                readyPlayer1 = true
                if(readyPlayer1 && readyPlayer2){
                    result()
                }
                pemain1.forEach {it.isClickable = false}
            }
        }

        pemain2.forEach {
            it.setOnClickListener {
                it.setBackgroundResource(R.drawable.ic_backgroud_clik)
                batuCom.isClickable = false
                guntingCom.isClickable = false
                kertasCom.isClickable = false
                controller.setPlayer2(pilihan[pemain2.indexOf(it)])
                Toast.makeText(this, "Pemain 2 Memilih ${controller.getPlayer2()}", Toast.LENGTH_SHORT).show()
                Log.e("MainActivity", "Pemain 2 Memilih ${controller.getPlayer2()}")
                readyPlayer2 = true
                if(readyPlayer1 && readyPlayer2){
                    result()
                }
                pemain2.forEach {it.isClickable = false}
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
                pemenang.text = "Pemain 2\nMENANG!"
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
        readyPlayer1 = false
        readyPlayer2 = false

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
}