package com.example.chapter5
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.snackbar.Snackbar
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class LandingActivity : AppCompatActivity() {
    private var name: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)
        val viewpager2 by lazy {this.findViewById<ViewPager2>(R.id.viewpager2)}
        val dots_indicator by lazy {this.findViewById<DotsIndicator>(R.id.dots_indicator)}
        val btn1 by lazy {this.findViewById<ImageView>(R.id.ivNext)}
        val viewPagerAdapter = ViewPagerAdaptor(this) {
            name = it.toString()
        }

        viewpager2.adapter = viewPagerAdapter
        dots_indicator.setViewPager2(viewpager2)

        btn1.setOnClickListener {
            if (viewpager2.currentItem < 2) {
                viewpager2.currentItem = viewpager2.currentItem.plus(1)
            } else if (name != "") {
                Intent(this, MainActivity::class.java)
                .apply {
                    putExtra("Name", name)
                    startActivity(this)
                    finish()
                }
            } else {
                Toast.makeText(this, "Isi nama terlebih dahulu", Toast.LENGTH_SHORT).show()
            }
        }
    }
}