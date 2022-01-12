package com.ticoo.tripto

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ticoo.tripto.adapters.Hospital_adapter
import com.ticoo.tripto.models.Hospital_model
import kotlinx.android.synthetic.main.activity_hospital.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream

class Hospital : AppCompatActivity() {
    private val mRecyclerViewItems = ArrayList<Hospital_model>()
    val adapter = Hospital_adapter(this, mRecyclerViewItems)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hospital)

        val mRecyclerView = findViewById<RecyclerView>(R.id.hospital_recycler)
        mRecyclerView.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        mRecyclerView.layoutManager = layoutManager

        mRecyclerView.adapter = adapter
        btn_near_hospitals.setOnClickListener {
            Toast.makeText(this,"Near Places will show nearly ! ",Toast.LENGTH_SHORT).show()
        }
        val myPrefs=applicationContext.getSharedPreferences("Setting", Context.MODE_PRIVATE)
        val lang=myPrefs.getString("lang","")
        val city=myPrefs.getString("city","")

        if(lang=="English"&&city=="Cairo"){addMenuItemsFromJson()}
        else if (lang=="Arabic"&&city=="Cairo"){addMenuItemsFromarJson()}
        else if (lang=="French"&&city=="Cairo"){addMenuItemsFromfrJson()}

        else if (lang=="English"&&city=="Alexandria"){addMenuItemsFromalexJson()}
        else if (lang=="Arabic"&&city=="Alexandria"){addMenuItemsFromaralexJson()}
        else if (lang=="French"&&city=="Alexandria"){addMenuItemsFromfralexJson()}

        else if (lang=="English"&&city=="Luxor"){addMenuItemsFromluxorJson()}
        else if (lang=="Arabic"&&city=="Luxor"){addMenuItemsFromarluxorJson()}
        else if (lang=="French"&&city=="Luxor"){addMenuItemsFromfrluxorJson()}

        else if (lang=="English"&&city=="Aswan"){addMenuItemsFromaswanJson()}
        else if (lang=="Arabic"&&city=="Aswan"){addMenuItemsFromaraswanJson()}
        else if (lang=="French"&&city=="Aswan"){addMenuItemsFromfraswanJson()}

        else{Toast.makeText(this,"Error in Selected City or Language ",Toast.LENGTH_SHORT).show()}
        hospital_search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                filter(s.toString())
            }
        })
        btn_near_hospitals.setOnClickListener {
            startActivity(Intent(this,Map_near::class.java))
        }
    }
    private fun filter(text: String) {
        val filteredList = ArrayList<Hospital_model>()

        for (item in mRecyclerViewItems) {
            if (item.name!!.toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item)
            }
        }
        adapter.filterList(filteredList)
    }
    //English Language cairo
    private fun addMenuItemsFromJson() {
        try {
            val jObject = JSONObject(readJsonDataFromFile())
            val menuItemsJsonArray = jObject.getJSONArray("cairo_hospitals")
            for (i in 0 until  menuItemsJsonArray.length()) {
                val menuItemObject = menuItemsJsonArray.getJSONObject(i)
                val name = menuItemObject.getString("name")
                val address = menuItemObject.getString("location")
                val phone = menuItemObject.getString("telephone")
                val photo = menuItemObject.getString("photo")

                val map_location=menuItemObject.getJSONObject("location_map")
                val lng=map_location.getString("lang")
                val lat=map_location.getString("late")
                val Hospital_model = Hospital_model(name,address,phone,photo,lat,lng)
                mRecyclerViewItems.add(Hospital_model)
            }
        }
        catch (exception: JSONException) {
            Log.e(Hotels::class.java.name, "Unable to parse JSON/JSON file.", exception)
            Toast.makeText(this,"Error : Server not working Successfully!", Toast.LENGTH_LONG).show()
        }
    }

    @Throws(IOException::class)
    private fun readJsonDataFromFile(): String {
        var json: String? = null
        try {
            val  inputStream: InputStream = assets.open("all_hospitals.json")
            json = inputStream.bufferedReader().use{it.readText()}
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        return json!!
    }

    //English Language Alex
    private fun addMenuItemsFromalexJson() {
        try {
            val jObject = JSONObject(readJsonDataFromalexFile())
            val menuItemsJsonArray = jObject.getJSONArray("alexandria_hospitals")
            for (i in 0 until  menuItemsJsonArray.length()) {
                val menuItemObject = menuItemsJsonArray.getJSONObject(i)
                val name = menuItemObject.getString("name")
                val address = menuItemObject.getString("location")
                val phone = menuItemObject.getString("telephone")
                val photo = menuItemObject.getString("photo")

                val map_location=menuItemObject.getJSONObject("location_map")
                val lng=map_location.getString("lang")
                val lat=map_location.getString("late")
                val Hospital_model = Hospital_model(name,address,phone,photo,lat,lng)
                mRecyclerViewItems.add(Hospital_model)
            }
        }
        catch (exception: JSONException) {
            Log.e(Hotels::class.java.name, "Unable to parse JSON/JSON file.", exception)
            Toast.makeText(this,"Error : Server not working Successfully!", Toast.LENGTH_LONG).show()
        }
    }

    @Throws(IOException::class)
    private fun readJsonDataFromalexFile(): String {
        var json: String? = null
        try {
            val  inputStream: InputStream = assets.open("all_hospitals.json")
            json = inputStream.bufferedReader().use{it.readText()}
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        return json!!
    }
    //English Language Luxor
    private fun addMenuItemsFromluxorJson() {
        try {
            val jObject = JSONObject(readJsonDataFromluxorFile())
            val menuItemsJsonArray = jObject.getJSONArray("luxor_hospitals")
            for (i in 0 until  menuItemsJsonArray.length()) {
                val menuItemObject = menuItemsJsonArray.getJSONObject(i)
                val name = menuItemObject.getString("name")
                val address = menuItemObject.getString("location")
                val phone = menuItemObject.getString("telephone")
                val photo = menuItemObject.getString("photo")

                val map_location=menuItemObject.getJSONObject("location_map")
                val lng=map_location.getString("lang")
                val lat=map_location.getString("late")
                val Hospital_model = Hospital_model(name,address,phone,photo,lat,lng)
                mRecyclerViewItems.add(Hospital_model)
            }
        }
        catch (exception: JSONException) {
            Log.e(Hotels::class.java.name, "Unable to parse JSON/JSON file.", exception)
            Toast.makeText(this,"Error : Server not working Successfully!", Toast.LENGTH_LONG).show()
        }
    }

    @Throws(IOException::class)
    private fun readJsonDataFromluxorFile(): String {
        var json: String? = null
        try {
            val  inputStream: InputStream = assets.open("all_hospitals.json")
            json = inputStream.bufferedReader().use{it.readText()}
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        return json!!
    }
    //English Language Alex
    private fun addMenuItemsFromaswanJson() {
        try {
            val jObject = JSONObject(readJsonDataFromaswanFile())
            val menuItemsJsonArray = jObject.getJSONArray("aswan_hospitals")
            for (i in 0 until  menuItemsJsonArray.length()) {
                val menuItemObject = menuItemsJsonArray.getJSONObject(i)
                val name = menuItemObject.getString("name")
                val address = menuItemObject.getString("location")
                val phone = menuItemObject.getString("telephone")
                val photo = menuItemObject.getString("photo")

                val map_location=menuItemObject.getJSONObject("location_map")
                val lng=map_location.getString("lang")
                val lat=map_location.getString("late")
                val Hospital_model = Hospital_model(name,address,phone,photo,lat,lng)
                mRecyclerViewItems.add(Hospital_model)
            }
        }
        catch (exception: JSONException) {
            Log.e(Hotels::class.java.name, "Unable to parse JSON/JSON file.", exception)
            Toast.makeText(this,"Error : Server not working Successfully!", Toast.LENGTH_LONG).show()
        }
    }

    @Throws(IOException::class)
    private fun readJsonDataFromaswanFile(): String {
        var json: String? = null
        try {
            val  inputStream: InputStream = assets.open("all_hospitals.json")
            json = inputStream.bufferedReader().use{it.readText()}
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        return json!!
    }

    //Arabic Language cairo
    private fun addMenuItemsFromarJson() {
        try {
            val jObject = JSONObject(readJsonDataFromarFile())
            val menuItemsJsonArray = jObject.getJSONArray("cairo_hospitals")
            for (i in 0 until  menuItemsJsonArray.length()) {
                val menuItemObject = menuItemsJsonArray.getJSONObject(i)
                val name = menuItemObject.getString("name")
                val address = menuItemObject.getString("location")
                val phone = menuItemObject.getString("telephone")
                val photo = menuItemObject.getString("photo")

                val map_location=menuItemObject.getJSONObject("location_map")
                val lng=map_location.getString("lang")
                val lat=map_location.getString("late")
                val Hospital_model = Hospital_model(name,address,phone,photo,lat,lng)
                mRecyclerViewItems.add(Hospital_model)
            }
        }
        catch (exception: JSONException) {
            Log.e(Hotels::class.java.name, "Unable to parse JSON/JSON file.", exception)
            Toast.makeText(this,"Error : Server not working Successfully!", Toast.LENGTH_LONG).show()
        }
    }

    @Throws(IOException::class)
    private fun readJsonDataFromarFile(): String {
        var json: String? = null
        try {
            val  inputStream: InputStream = assets.open("all_hospitals_ar.json")
            json = inputStream.bufferedReader().use{it.readText()}
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        return json!!
    }

    //Arabic Language Alex
    private fun addMenuItemsFromaralexJson() {
        try {
            val jObject = JSONObject(readJsonDataFromaralexFile())
            val menuItemsJsonArray = jObject.getJSONArray("alexandria_hospitals")
            for (i in 0 until  menuItemsJsonArray.length()) {
                val menuItemObject = menuItemsJsonArray.getJSONObject(i)
                val name = menuItemObject.getString("name")
                val address = menuItemObject.getString("location")
                val phone = menuItemObject.getString("telephone")
                val photo = menuItemObject.getString("photo")

                val map_location=menuItemObject.getJSONObject("location_map")
                val lng=map_location.getString("lang")
                val lat=map_location.getString("late")
                val Hospital_model = Hospital_model(name,address,phone,photo,lat,lng)
                mRecyclerViewItems.add(Hospital_model)
            }
        }
        catch (exception: JSONException) {
            Log.e(Hotels::class.java.name, "Unable to parse JSON/JSON file.", exception)
            Toast.makeText(this,"Error : Server not working Successfully!", Toast.LENGTH_LONG).show()
        }
    }

    @Throws(IOException::class)
    private fun readJsonDataFromaralexFile(): String {
        var json: String? = null
        try {
            val  inputStream: InputStream = assets.open("all_hospitals_ar.json")
            json = inputStream.bufferedReader().use{it.readText()}
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        return json!!
    }

    //Arabic Language Alex
    private fun addMenuItemsFromarluxorJson() {
        try {
            val jObject = JSONObject(readJsonDataFromarluxorFile())
            val menuItemsJsonArray = jObject.getJSONArray("luxor_hospitals")
            for (i in 0 until  menuItemsJsonArray.length()) {
                val menuItemObject = menuItemsJsonArray.getJSONObject(i)
                val name = menuItemObject.getString("name")
                val address = menuItemObject.getString("location")
                val phone = menuItemObject.getString("telephone")
                val photo = menuItemObject.getString("photo")

                val map_location=menuItemObject.getJSONObject("location_map")
                val lng=map_location.getString("lang")
                val lat=map_location.getString("late")
                val Hospital_model = Hospital_model(name,address,phone,photo,lat,lng)
                mRecyclerViewItems.add(Hospital_model)
            }
        }
        catch (exception: JSONException) {
            Log.e(Hotels::class.java.name, "Unable to parse JSON/JSON file.", exception)
            Toast.makeText(this,"Error : Server not working Successfully!", Toast.LENGTH_LONG).show()
        }
    }

    @Throws(IOException::class)
    private fun readJsonDataFromarluxorFile(): String {
        var json: String? = null
        try {
            val  inputStream: InputStream = assets.open("all_hospitals_ar.json")
            json = inputStream.bufferedReader().use{it.readText()}
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        return json!!
    }

    //Arabic Language Aswan
    private fun addMenuItemsFromaraswanJson() {
        try {
            val jObject = JSONObject(readJsonDataFromaraswanFile())
            val menuItemsJsonArray = jObject.getJSONArray("aswan_hospitals")
            for (i in 0 until  menuItemsJsonArray.length()) {
                val menuItemObject = menuItemsJsonArray.getJSONObject(i)
                val name = menuItemObject.getString("name")
                val address = menuItemObject.getString("location")
                val phone = menuItemObject.getString("telephone")
                val photo = menuItemObject.getString("photo")

                val map_location=menuItemObject.getJSONObject("location_map")
                val lng=map_location.getString("lang")
                val lat=map_location.getString("late")
                val Hospital_model = Hospital_model(name,address,phone,photo,lat,lng)
                mRecyclerViewItems.add(Hospital_model)
            }
        }
        catch (exception: JSONException) {
            Log.e(Hotels::class.java.name, "Unable to parse JSON/JSON file.", exception)
            Toast.makeText(this,"Error : Server not working Successfully!", Toast.LENGTH_LONG).show()
        }
    }

    @Throws(IOException::class)
    private fun readJsonDataFromaraswanFile(): String {
        var json: String? = null
        try {
            val  inputStream: InputStream = assets.open("all_hospitals_ar.json")
            json = inputStream.bufferedReader().use{it.readText()}
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        return json!!
    }
    //French Language cairo
    private fun addMenuItemsFromfrJson() {
        try {
            val jObject = JSONObject(readJsonDataFromfrFile())
            val menuItemsJsonArray = jObject.getJSONArray("cairo_hospitals")
            for (i in 0 until  menuItemsJsonArray.length()) {
                val menuItemObject = menuItemsJsonArray.getJSONObject(i)
                val name = menuItemObject.getString("name")
                val address = menuItemObject.getString("location")
                val phone = menuItemObject.getString("telephone")
                val photo = menuItemObject.getString("photo")

                val map_location=menuItemObject.getJSONObject("location_map")
                val lng=map_location.getString("lang")
                val lat=map_location.getString("late")
                val Hospital_model = Hospital_model(name,address,phone,photo,lat,lng)
                mRecyclerViewItems.add(Hospital_model)
            }
        }
        catch (exception: JSONException) {
            Log.e(Hotels::class.java.name, "Unable to parse JSON/JSON file.", exception)
            Toast.makeText(this,"Error : Server not working Successfully!", Toast.LENGTH_LONG).show()
        }
    }

    @Throws(IOException::class)
    private fun readJsonDataFromfrFile(): String {
        var json: String? = null
        try {
            val  inputStream: InputStream = assets.open("all_hospitals_fr.json")
            json = inputStream.bufferedReader().use{it.readText()}
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        return json!!
    }

    //French Language Alex
    private fun addMenuItemsFromfralexJson() {
        try {
            val jObject = JSONObject(readJsonDataFromfralexFile())
            val menuItemsJsonArray = jObject.getJSONArray("alexandria_hospitals")
            for (i in 0 until  menuItemsJsonArray.length()) {
                val menuItemObject = menuItemsJsonArray.getJSONObject(i)
                val name = menuItemObject.getString("name")
                val address = menuItemObject.getString("location")
                val phone = menuItemObject.getString("telephone")
                val photo = menuItemObject.getString("photo")

                val map_location=menuItemObject.getJSONObject("location_map")
                val lng=map_location.getString("lang")
                val lat=map_location.getString("late")
                val Hospital_model = Hospital_model(name,address,phone,photo,lat,lng)
                mRecyclerViewItems.add(Hospital_model)
            }
        }
        catch (exception: JSONException) {
            Log.e(Hotels::class.java.name, "Unable to parse JSON/JSON file.", exception)
            Toast.makeText(this,"Error : Server not working Successfully!", Toast.LENGTH_LONG).show()
        }
    }

    @Throws(IOException::class)
    private fun readJsonDataFromfralexFile(): String {
        var json: String? = null
        try {
            val  inputStream: InputStream = assets.open("all_hospitals_fr.json")
            json = inputStream.bufferedReader().use{it.readText()}
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        return json!!
    }

    //French Language Luxor
    private fun addMenuItemsFromfrluxorJson() {
        try {
            val jObject = JSONObject(readJsonDataFromfrluxorFile())
            val menuItemsJsonArray = jObject.getJSONArray("luxor_hospitals")
            for (i in 0 until  menuItemsJsonArray.length()) {
                val menuItemObject = menuItemsJsonArray.getJSONObject(i)
                val name = menuItemObject.getString("name")
                val address = menuItemObject.getString("location")
                val phone = menuItemObject.getString("telephone")
                val photo = menuItemObject.getString("photo")

                val map_location=menuItemObject.getJSONObject("location_map")
                val lng=map_location.getString("lang")
                val lat=map_location.getString("late")
                val Hospital_model = Hospital_model(name,address,phone,photo,lat,lng)
                mRecyclerViewItems.add(Hospital_model)
            }
        }
        catch (exception: JSONException) {
            Log.e(Hotels::class.java.name, "Unable to parse JSON/JSON file.", exception)
            Toast.makeText(this,"Error : Server not working Successfully!", Toast.LENGTH_LONG).show()
        }
    }

    @Throws(IOException::class)
    private fun readJsonDataFromfrluxorFile(): String {
        var json: String? = null
        try {
            val  inputStream: InputStream = assets.open("all_hospitals_fr.json")
            json = inputStream.bufferedReader().use{it.readText()}
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        return json!!
    }

    //French Language Aswan
    private fun addMenuItemsFromfraswanJson() {
        try {
            val jObject = JSONObject(readJsonDataFromfraswanFile())
            val menuItemsJsonArray = jObject.getJSONArray("aswan_hospitals")
            for (i in 0 until  menuItemsJsonArray.length()) {
                val menuItemObject = menuItemsJsonArray.getJSONObject(i)
                val name = menuItemObject.getString("name")
                val address = menuItemObject.getString("location")
                val phone = menuItemObject.getString("telephone")
                val photo = menuItemObject.getString("photo")

                val map_location=menuItemObject.getJSONObject("location_map")
                val lng=map_location.getString("lang")
                val lat=map_location.getString("late")
                val Hospital_model = Hospital_model(name,address,phone,photo,lat,lng)
                mRecyclerViewItems.add(Hospital_model)
            }
        }
        catch (exception: JSONException) {
            Log.e(Hotels::class.java.name, "Unable to parse JSON/JSON file.", exception)
            Toast.makeText(this,"Error : Server not working Successfully!", Toast.LENGTH_LONG).show()
        }
    }

    @Throws(IOException::class)
    private fun readJsonDataFromfraswanFile(): String {
        var json: String? = null
        try {
            val  inputStream: InputStream = assets.open("all_hospitals_fr.json")
            json = inputStream.bufferedReader().use{it.readText()}
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        return json!!
    }
}
