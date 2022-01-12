package com.ticoo.tripto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.splash.*
import java.util.*

class Splash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash)


     val logo_anim:Animation = AnimationUtils.loadAnimation(this, R.anim.animation)
     val appname_anim:Animation = AnimationUtils.loadAnimation(this, R.anim.animation2)
        txt_applogo.startAnimation(logo_anim)
        txt_appname.startAnimation(appname_anim)

        Timer().schedule(object : TimerTask() {
            override fun run() {
                val i = Intent(baseContext, Select_lang::class.java)
                startActivity(i)
            }
        },4000)
    }
    override fun onRestart() {
        super.onRestart()
        finish()
    }

    }

