package com.ticoo.tripto

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_select_lang.*
import android.widget.AdapterView
import android.widget.Toast
import android.widget.AdapterView.OnItemSelectedListener

class Select_lang : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_lang)
        val myPrefs=applicationContext.getSharedPreferences("Setting", Context.MODE_PRIVATE)
        val editor = myPrefs.edit()
        lang_spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                editor.putString("lang",lang_spinner.selectedItem.toString())
                editor.apply()
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                // TODO Auto-generated method stub
            }
        }

        city_spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                editor.putString("city",city_spinner.selectedItem.toString())
                editor.apply()
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                // TODO Auto-generated method stub
            }
        }

        btn_ok.setOnClickListener {
            val lang=myPrefs.getString("lang","")
            val city=myPrefs.getString("city","")
            if(lang=="English"&&city=="Cairo"){
            val i = Intent(baseContext, Home::class.java)
            startActivity(i)
            }
            else if(lang=="English"&&city=="Alexandria"){
                val i = Intent(baseContext, Home::class.java)
                startActivity(i)
            }
            else if(lang=="English"&&city=="Aswan"){
                val i = Intent(baseContext, Home::class.java)
                startActivity(i)
            }
            else if(lang=="English"&&city=="Luxor"){
                val i = Intent(baseContext, Home::class.java)
                startActivity(i)
            }
            else if(lang=="Arabic"&&city=="Cairo"){
                val i = Intent(baseContext, Home::class.java)
                startActivity(i)
            }
             else if(lang=="Arabic"&&city=="Alexandria"){
                val i = Intent(baseContext, Home::class.java)
                startActivity(i)
            }
            else if(lang=="Arabic"&&city=="Aswan"){
                val i = Intent(baseContext, Home::class.java)
                startActivity(i)
            }
            else if(lang=="Arabic"&&city=="Luxor"){
                val i = Intent(baseContext, Home::class.java)
                startActivity(i)
            }
            else if(lang=="French"&&city=="Cairo"){
                val i = Intent(baseContext, Home::class.java)
                startActivity(i)
            }
            else if(lang=="French"&&city=="Alexandria"){
                val i = Intent(baseContext, Home::class.java)
                startActivity(i)
            }
            else if(lang=="French"&&city=="Aswan"){
                val i = Intent(baseContext, Home::class.java)
                startActivity(i)
            }
            else if(lang=="French"&&city=="Luxor"){
                val i = Intent(baseContext, Home::class.java)
                startActivity(i)
            }
            else{Toast.makeText(this,"You must Select Language and City",Toast.LENGTH_SHORT).show()}
        }
    }
}
