package com.ticoo.tripto

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_home.*
import java.util.*

class Home : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val myPrefs=applicationContext.getSharedPreferences("Setting", Context.MODE_PRIVATE)
        val lang=myPrefs.getString("lang","")
        val city=myPrefs.getString("city","")
        val cario_imageArray = intArrayOf(R.drawable.c_h1, R.drawable.c_h2, R.drawable.c_h3, R.drawable.c_h4, R.drawable.c_h5, R.drawable.c_h6)
        val alex_imageArray = intArrayOf(R.drawable.a_h1, R.drawable.a_h2, R.drawable.a_h3, R.drawable.a_h4, R.drawable.a_h5, R.drawable.a_h6)
        val aswan_imageArray = intArrayOf(R.drawable.as_h1, R.drawable.as_h2, R.drawable.as_h3, R.drawable.as_h4, R.drawable.as_h5, R.drawable.as_h6)
        val luxor_imageArray = intArrayOf(R.drawable.l_h1, R.drawable.l_h2, R.drawable.l_h3, R.drawable.l_h4, R.drawable.l_h5, R.drawable.l_h6)
        when (city) {
            "Cairo" -> for ( image in cario_imageArray){
                flip_images(image)
            }
            "Alexandria" -> for ( image in alex_imageArray){
                flip_images(image)
            }
            "Aswan" -> for ( image in aswan_imageArray){
                flip_images(image)
            }
            "Luxor" -> for ( image in luxor_imageArray){
                flip_images(image)
            }
            else -> for ( image in cario_imageArray){
                flip_images(image)
            }
        }
        when (lang) {
            "Arabic" ->setLocate("ar")
            "English" ->setLocate("en")
            "French" ->setLocate("fr")
        }
        btn_hotel.setOnClickListener { startActivity(Intent(this, Hotels::class.java))}
        btn_hospital.setOnClickListener { startActivity(Intent(this, Hospital::class.java))}
        btn_cafe.setOnClickListener { startActivity(Intent(this, Cafe::class.java))}
        btn_places.setOnClickListener {startActivity(Intent(this, Places::class.java)) }
        btn_pieces.setOnClickListener { startActivity(Intent(this,Museums::class.java)) }


        val tf : Typeface = Typeface.createFromAsset(assets,"Cairo_Regular.ttf")
        txt_hotel.typeface = tf
        txt_hospital.typeface = tf
        txt_cafe.typeface = tf
        txt_places.typeface=tf
        txt_pieces.typeface=tf
    }

    override fun onBackPressed() {
        finish()
    }
    fun flip_images(image:Int){
    val image_view=ImageView(this)
    image_view.setBackgroundResource(image)
    auto_image.addView(image_view)
    auto_image.setFlipInterval(3000)
    auto_image.isAutoStart=true
    auto_image.setInAnimation(this,android.R.anim.slide_in_left)
    auto_image.setOutAnimation(this,android.R.anim.slide_out_right)
    }

    private fun setLocate(Lang: String) {
        val locale = Locale(Lang)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)
    }
}
