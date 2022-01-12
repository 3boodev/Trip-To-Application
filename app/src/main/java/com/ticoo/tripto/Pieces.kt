package com.ticoo.tripto

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ticoo.tripto.adapters.Pieces_adapter
import com.ticoo.tripto.models.Pieces_model
import kotlinx.android.synthetic.main.activity_pieces.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream

class Pieces : AppCompatActivity() {
    private val mRecyclerViewItems = ArrayList<Pieces_model>()
    val adapter = Pieces_adapter(this, mRecyclerViewItems)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pieces)

        val mRecyclerView = findViewById<RecyclerView>(R.id.pieces_recycler)
        mRecyclerView.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        mRecyclerView.layoutManager = layoutManager
        mRecyclerView.adapter = adapter
        val myPrefs=applicationContext.getSharedPreferences("Setting", Context.MODE_PRIVATE)
        val lang=myPrefs.getString("lang","")
        val museum_pref=applicationContext.getSharedPreferences("Museums", Context.MODE_PRIVATE)
        val museums=museum_pref.getString("museums","")
        if(lang=="English"&&museums=="The Egyptian Museum"){addMenuItemsFromJson()}
        else if(lang=="English"&&museums=="The Coptic Museum"){addMenuItemsFromJson_Coptic()}
        else if(lang=="English"&&museums=="The Museum of Islamic Arts"){addMenuItemsFromJson_Islamic()}
        else if(lang=="English"&&museums=="The Manial Palace Museum"){addMenuItemsFromJson_Manial()}
        else if(lang=="English"&&museums=="Barque Solaire Museum"){addMenuItemsFromJson_Solaire()}
        else if(lang=="English"&&museums=="Abdeen Palace Museum"){addMenuItemsFromJson_Abdeen()}
        else if(lang=="English"&&museums=="Imhotep Museum"){addMenuItemsFromJson_Imhotep()}
        else if(lang=="English"&&museums=="The Grand Egyptian Museum"){addMenuItemsFromJson_Grand()}
        else if(lang=="English"&&museums=="Alexandria National Museum"){addMenuItemsFromJson_Alexandria_National()}
        else if(lang=="English"&&museums=="Royal Jewelry Museum"){addMenuItemsFromJson_Royal()}
        else if(lang=="English"&&museums=="Library Alexandria Museum"){addMenuItemsFromJson_Library_Alexandria()}
        else if(lang=="English"&&museums=="The Open Museum at Karnak"){addMenuItemsFromJson_Open_Museum()}
        else if(lang=="English"&&museums=="Luxor Museum"){addMenuItemsFromJson_Luxor_Museum()}
        else if(lang=="English"&&museums=="Mummification Museum"){addMenuItemsFromJson_Mummification_Museum()}
        else if(lang=="English"&&museums=="Crocodile Museum"){addMenuItemsFromJson_Crocodile_Museum()}
        else if(lang=="English"&&museums=="Nubia Museum"){addMenuItemsFromJson_Nubia_Museum()}
        else if(lang=="Arabic"&&museums=="The Egyptian Museum"){addMenuItemsFromJson_ar_The_Egyptian_Museum()}
        else if(lang=="French"&&museums=="The Egyptian Museum"){addMenuItemsFromJson_fr_The_Egyptian_Museum()}


        pieces_search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable) {
                filter(s.toString())
            }
        })
    }
    //search in rectcler view
    private fun filter(text: String) {
        val filteredList = ArrayList<Pieces_model>()
        for (item in mRecyclerViewItems) {
            if (item.name!!.toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item)
            }
        }
        adapter.filterList(filteredList)
    }

    //English Language egyptian_museum_artifacts
    private fun addMenuItemsFromJson() {
        try {
            val jObject = JSONObject(readJsonDataFromFile())
            val menuItemsJsonArray = jObject.getJSONArray("egyptian_museum_artifacts")
            for (i in 0 until  menuItemsJsonArray.length()) {
                val menuItemObject = menuItemsJsonArray.getJSONObject(i)
                val name = menuItemObject.getString("name")
                val description = menuItemObject.getString("description")
                val date = menuItemObject.getString("date")
                val culture = menuItemObject.getString("culture")
                val period = menuItemObject.getString("name")
                val place_of_origin = menuItemObject.getString("place of origin")
                val type_of_object = menuItemObject.getString("type of object")
                val dimension_details = menuItemObject.getString("dimension details")
                val material = menuItemObject.getString("material")
                val photo = menuItemObject.getString("photo")
                val Pieces_model = Pieces_model(name,description,date,culture,period,place_of_origin,type_of_object,dimension_details,material,photo)
                mRecyclerViewItems.add(Pieces_model)
            }
        }
        catch (exception: JSONException) {
            Log.e(Hotels::class.java.name, "Unable to parse JSON/JSON file.", exception)
            Toast.makeText(this,"Error : Server not working Successfully!", Toast.LENGTH_LONG).show()
        }
    }

    //English Language coptic_museum_artifacts
    private fun addMenuItemsFromJson_Coptic() {
        try {
            val jObject = JSONObject(readJsonDataFromFile())
            val menuItemsJsonArray = jObject.getJSONArray("coptic_museum_artifacts")
            for (i in 0 until  menuItemsJsonArray.length()) {
                val menuItemObject = menuItemsJsonArray.getJSONObject(i)
                val name = menuItemObject.getString("name")
                val description = menuItemObject.getString("description")
                val date = menuItemObject.getString("date")
                val culture = menuItemObject.getString("culture")
                val period = menuItemObject.getString("name")
                val place_of_origin = menuItemObject.getString("place of origin")
                val type_of_object = menuItemObject.getString("type of object")
                val dimension_details = menuItemObject.getString("dimension details")
                val material = menuItemObject.getString("material")
                val photo = menuItemObject.getString("photo")
                val Pieces_model = Pieces_model(name,description,date,culture,period,place_of_origin,type_of_object,dimension_details,material,photo)
                mRecyclerViewItems.add(Pieces_model)
            }
        }
        catch (exception: JSONException) {
            Log.e(Hotels::class.java.name, "Unable to parse JSON/JSON file.", exception)
            Toast.makeText(this,"Error : Server not working Successfully!", Toast.LENGTH_LONG).show()
        }
    }

    //English Language museum_islamic_arts_artifacts
    private fun addMenuItemsFromJson_Islamic() {
        try {
            val jObject = JSONObject(readJsonDataFromFile())
            val menuItemsJsonArray = jObject.getJSONArray("museum_islamic_arts_artifacts")
            for (i in 0 until  menuItemsJsonArray.length()) {
                val menuItemObject = menuItemsJsonArray.getJSONObject(i)
                val name = menuItemObject.getString("name")
                val description = menuItemObject.getString("description")
                val date = menuItemObject.getString("date")
                val culture = menuItemObject.getString("culture")
                val period = menuItemObject.getString("name")
                val place_of_origin = menuItemObject.getString("place of origin")
                val type_of_object = menuItemObject.getString("type of object")
                val dimension_details = menuItemObject.getString("dimension details")
                val material = menuItemObject.getString("material")
                val photo = menuItemObject.getString("photo")
                val Pieces_model = Pieces_model(name,description,date,culture,period,place_of_origin,type_of_object,dimension_details,material,photo)
                mRecyclerViewItems.add(Pieces_model)
            }
        }
        catch (exception: JSONException) {
            Log.e(Hotels::class.java.name, "Unable to parse JSON/JSON file.", exception)
            Toast.makeText(this,"Error : Server not working Successfully!", Toast.LENGTH_LONG).show()
        }
    }

    //English Language barque_solaire_museum_artifacts
    private fun addMenuItemsFromJson_Manial() {
        try {
            val jObject = JSONObject(readJsonDataFromFile())
            val menuItemsJsonArray = jObject.getJSONArray("barque_solaire_museum_artifacts")
            for (i in 0 until  menuItemsJsonArray.length()) {
                val menuItemObject = menuItemsJsonArray.getJSONObject(i)
                val name = menuItemObject.getString("name")
                val description = menuItemObject.getString("description")
                val date = menuItemObject.getString("date")
                val culture = menuItemObject.getString("culture")
                val period = menuItemObject.getString("name")
                val place_of_origin = menuItemObject.getString("place of origin")
                val type_of_object = menuItemObject.getString("type of object")
                val dimension_details = menuItemObject.getString("dimension details")
                val material = menuItemObject.getString("material")
                val photo = menuItemObject.getString("photo")
                val Pieces_model = Pieces_model(name,description,date,culture,period,place_of_origin,type_of_object,dimension_details,material,photo)
                mRecyclerViewItems.add(Pieces_model)
            }
        }
        catch (exception: JSONException) {
            Log.e(Hotels::class.java.name, "Unable to parse JSON/JSON file.", exception)
            Toast.makeText(this,"Error : Server not working Successfully!", Toast.LENGTH_LONG).show()
        }
    }
    //English Language manial_palace_museum_artifacts
    private fun addMenuItemsFromJson_Solaire() {
        try {
            val jObject = JSONObject(readJsonDataFromFile())
            val menuItemsJsonArray = jObject.getJSONArray("manial_palace_museum_artifacts")
            for (i in 0 until  menuItemsJsonArray.length()) {
                val menuItemObject = menuItemsJsonArray.getJSONObject(i)
                val name = menuItemObject.getString("name")
                val description = menuItemObject.getString("description")
                val date = menuItemObject.getString("date")
                val culture = menuItemObject.getString("culture")
                val period = menuItemObject.getString("name")
                val place_of_origin = menuItemObject.getString("place of origin")
                val type_of_object = menuItemObject.getString("type of object")
                val dimension_details = menuItemObject.getString("dimension details")
                val material = menuItemObject.getString("material")
                val photo = menuItemObject.getString("photo")
                val Pieces_model = Pieces_model(name,description,date,culture,period,place_of_origin,type_of_object,dimension_details,material,photo)
                mRecyclerViewItems.add(Pieces_model)
            }
        }
        catch (exception: JSONException) {
            Log.e(Hotels::class.java.name, "Unable to parse JSON/JSON file.", exception)
            Toast.makeText(this,"Error : Server not working Successfully!", Toast.LENGTH_LONG).show()
        }
    }
    //English Language abdeen_palace_museum_artifacts
    private fun addMenuItemsFromJson_Abdeen() {
        try {
            val jObject = JSONObject(readJsonDataFromFile())
            val menuItemsJsonArray = jObject.getJSONArray("abdeen_palace_museum_artifacts")
            for (i in 0 until  menuItemsJsonArray.length()) {
                val menuItemObject = menuItemsJsonArray.getJSONObject(i)
                val name = menuItemObject.getString("name")
                val description = menuItemObject.getString("description")
                val date = menuItemObject.getString("date")
                val culture = menuItemObject.getString("culture")
                val period = menuItemObject.getString("name")
                val place_of_origin = menuItemObject.getString("place of origin")
                val type_of_object = menuItemObject.getString("type of object")
                val dimension_details = menuItemObject.getString("dimension details")
                val material = menuItemObject.getString("material")
                val photo = menuItemObject.getString("photo")
                val Pieces_model = Pieces_model(name,description,date,culture,period,place_of_origin,type_of_object,dimension_details,material,photo)
                mRecyclerViewItems.add(Pieces_model)
            }
        }
        catch (exception: JSONException) {
            Log.e(Hotels::class.java.name, "Unable to parse JSON/JSON file.", exception)
            Toast.makeText(this,"Error : Server not working Successfully!", Toast.LENGTH_LONG).show()
        }
    }
    //English Language imhotep_museum_artifacts
    private fun addMenuItemsFromJson_Imhotep() {
        try {
            val jObject = JSONObject(readJsonDataFromFile())
            val menuItemsJsonArray = jObject.getJSONArray("imhotep_museum_artifacts")
            for (i in 0 until  menuItemsJsonArray.length()) {
                val menuItemObject = menuItemsJsonArray.getJSONObject(i)
                val name = menuItemObject.getString("name")
                val description = menuItemObject.getString("description")
                val date = menuItemObject.getString("date")
                val culture = menuItemObject.getString("culture")
                val period = menuItemObject.getString("name")
                val place_of_origin = menuItemObject.getString("place of origin")
                val type_of_object = menuItemObject.getString("type of object")
                val dimension_details = menuItemObject.getString("dimension details")
                val material = menuItemObject.getString("material")
                val photo = menuItemObject.getString("photo")
                val Pieces_model = Pieces_model(name,description,date,culture,period,place_of_origin,type_of_object,dimension_details,material,photo)
                mRecyclerViewItems.add(Pieces_model)
            }
        }
        catch (exception: JSONException) {
            Log.e(Hotels::class.java.name, "Unable to parse JSON/JSON file.", exception)
            Toast.makeText(this,"Error : Server not working Successfully!", Toast.LENGTH_LONG).show()
        }
    }
    //English Language grand_egyptian_museum_artifacts
    private fun addMenuItemsFromJson_Grand() {
        try {
            val jObject = JSONObject(readJsonDataFromFile())
            val menuItemsJsonArray = jObject.getJSONArray("grand_egyptian_museum_artifacts")
            for (i in 0 until  menuItemsJsonArray.length()) {
                val menuItemObject = menuItemsJsonArray.getJSONObject(i)
                val name = menuItemObject.getString("name")
                val description = menuItemObject.getString("description")
                val date = menuItemObject.getString("date")
                val culture = menuItemObject.getString("culture")
                val period = menuItemObject.getString("name")
                val place_of_origin = menuItemObject.getString("place of origin")
                val type_of_object = menuItemObject.getString("type of object")
                val dimension_details = menuItemObject.getString("dimension details")
                val material = menuItemObject.getString("material")
                val photo = menuItemObject.getString("photo")
                val Pieces_model = Pieces_model(name,description,date,culture,period,place_of_origin,type_of_object,dimension_details,material,photo)
                mRecyclerViewItems.add(Pieces_model)
            }
        }
        catch (exception: JSONException) {
            Log.e(Hotels::class.java.name, "Unable to parse JSON/JSON file.", exception)
            Toast.makeText(this,"Error : Server not working Successfully!", Toast.LENGTH_LONG).show()
        }
    }
    //English Language alexandria_national_museum_artifacts
    private fun addMenuItemsFromJson_Alexandria_National() {
        try {
            val jObject = JSONObject(readJsonDataFromFile())
            val menuItemsJsonArray = jObject.getJSONArray("alexandria_national_museum_artifacts")
            for (i in 0 until  menuItemsJsonArray.length()) {
                val menuItemObject = menuItemsJsonArray.getJSONObject(i)
                val name = menuItemObject.getString("name")
                val description = menuItemObject.getString("description")
                val date = menuItemObject.getString("date")
                val culture = menuItemObject.getString("culture")
                val period = menuItemObject.getString("name")
                val place_of_origin = menuItemObject.getString("place of origin")
                val type_of_object = menuItemObject.getString("type of object")
                val dimension_details = menuItemObject.getString("dimension details")
                val material = menuItemObject.getString("material")
                val photo = menuItemObject.getString("photo")
                val Pieces_model = Pieces_model(name,description,date,culture,period,place_of_origin,type_of_object,dimension_details,material,photo)
                mRecyclerViewItems.add(Pieces_model)
            }
        }
        catch (exception: JSONException) {
            Log.e(Hotels::class.java.name, "Unable to parse JSON/JSON file.", exception)
            Toast.makeText(this,"Error : Server not working Successfully!", Toast.LENGTH_LONG).show()
        }
    }
    //English Language royal_jewelry_museum_artifacts
    private fun addMenuItemsFromJson_Royal() {
        try {
            val jObject = JSONObject(readJsonDataFromFile())
            val menuItemsJsonArray = jObject.getJSONArray("royal_jewelry_museum_artifacts")
            for (i in 0 until  menuItemsJsonArray.length()) {
                val menuItemObject = menuItemsJsonArray.getJSONObject(i)
                val name = menuItemObject.getString("name")
                val description = menuItemObject.getString("description")
                val date = menuItemObject.getString("date")
                val culture = menuItemObject.getString("culture")
                val period = menuItemObject.getString("name")
                val place_of_origin = menuItemObject.getString("place of origin")
                val type_of_object = menuItemObject.getString("type of object")
                val dimension_details = menuItemObject.getString("dimension details")
                val material = menuItemObject.getString("material")
                val photo = menuItemObject.getString("photo")
                val Pieces_model = Pieces_model(name,description,date,culture,period,place_of_origin,type_of_object,dimension_details,material,photo)
                mRecyclerViewItems.add(Pieces_model)
            }
        }
        catch (exception: JSONException) {
            Log.e(Hotels::class.java.name, "Unable to parse JSON/JSON file.", exception)
            Toast.makeText(this,"Error : Server not working Successfully!", Toast.LENGTH_LONG).show()
        }
    }
    //English Language library_of_alexandria_museum_artifacts
    private fun addMenuItemsFromJson_Library_Alexandria() {
        try {
            val jObject = JSONObject(readJsonDataFromFile())
            val menuItemsJsonArray = jObject.getJSONArray("library_of_alexandria_museum_artifacts")
            for (i in 0 until  menuItemsJsonArray.length()) {
                val menuItemObject = menuItemsJsonArray.getJSONObject(i)
                val name = menuItemObject.getString("name")
                val description = menuItemObject.getString("description")
                val date = menuItemObject.getString("date")
                val culture = menuItemObject.getString("culture")
                val period = menuItemObject.getString("name")
                val place_of_origin = menuItemObject.getString("place of origin")
                val type_of_object = menuItemObject.getString("type of object")
                val dimension_details = menuItemObject.getString("dimension details")
                val material = menuItemObject.getString("material")
                val photo = menuItemObject.getString("photo")
                val Pieces_model = Pieces_model(name,description,date,culture,period,place_of_origin,type_of_object,dimension_details,material,photo)
                mRecyclerViewItems.add(Pieces_model)
            }
        }
        catch (exception: JSONException) {
            Log.e(Hotels::class.java.name, "Unable to parse JSON/JSON file.", exception)
            Toast.makeText(this,"Error : Server not working Successfully!", Toast.LENGTH_LONG).show()
        }
    }
    //English Language open_museum_at_karnak_artifacts
    private fun addMenuItemsFromJson_Open_Museum() {
        try {
            val jObject = JSONObject(readJsonDataFromFile())
            val menuItemsJsonArray = jObject.getJSONArray("open_museum_at_karnak_artifacts")
            for (i in 0 until  menuItemsJsonArray.length()) {
                val menuItemObject = menuItemsJsonArray.getJSONObject(i)
                val name = menuItemObject.getString("name")
                val description = menuItemObject.getString("description")
                val date = menuItemObject.getString("date")
                val culture = menuItemObject.getString("culture")
                val period = menuItemObject.getString("name")
                val place_of_origin = menuItemObject.getString("place of origin")
                val type_of_object = menuItemObject.getString("type of object")
                val dimension_details = menuItemObject.getString("dimension details")
                val material = menuItemObject.getString("material")
                val photo = menuItemObject.getString("photo")
                val Pieces_model = Pieces_model(name,description,date,culture,period,place_of_origin,type_of_object,dimension_details,material,photo)
                mRecyclerViewItems.add(Pieces_model)
            }
        }
        catch (exception: JSONException) {
            Log.e(Hotels::class.java.name, "Unable to parse JSON/JSON file.", exception)
            Toast.makeText(this,"Error : Server not working Successfully!", Toast.LENGTH_LONG).show()
        }
    }
    //English Language luxor_museum_artifacts
    private fun addMenuItemsFromJson_Luxor_Museum() {
        try {
            val jObject = JSONObject(readJsonDataFromFile())
            val menuItemsJsonArray = jObject.getJSONArray("luxor_museum_artifacts")
            for (i in 0 until  menuItemsJsonArray.length()) {
                val menuItemObject = menuItemsJsonArray.getJSONObject(i)
                val name = menuItemObject.getString("name")
                val description = menuItemObject.getString("description")
                val date = menuItemObject.getString("date")
                val culture = menuItemObject.getString("culture")
                val period = menuItemObject.getString("name")
                val place_of_origin = menuItemObject.getString("place of origin")
                val type_of_object = menuItemObject.getString("type of object")
                val dimension_details = menuItemObject.getString("dimension details")
                val material = menuItemObject.getString("material")
                val photo = menuItemObject.getString("photo")
                val Pieces_model = Pieces_model(name,description,date,culture,period,place_of_origin,type_of_object,dimension_details,material,photo)
                mRecyclerViewItems.add(Pieces_model)
            }
        }
        catch (exception: JSONException) {
            Log.e(Hotels::class.java.name, "Unable to parse JSON/JSON file.", exception)
            Toast.makeText(this,"Error : Server not working Successfully!", Toast.LENGTH_LONG).show()
        }
    }
    //English Language mummification_museum_artifacts
    private fun addMenuItemsFromJson_Mummification_Museum() {
        try {
            val jObject = JSONObject(readJsonDataFromFile())
            val menuItemsJsonArray = jObject.getJSONArray("mummification_museum_artifacts")
            for (i in 0 until  menuItemsJsonArray.length()) {
                val menuItemObject = menuItemsJsonArray.getJSONObject(i)
                val name = menuItemObject.getString("name")
                val description = menuItemObject.getString("description")
                val date = menuItemObject.getString("date")
                val culture = menuItemObject.getString("culture")
                val period = menuItemObject.getString("name")
                val place_of_origin = menuItemObject.getString("place of origin")
                val type_of_object = menuItemObject.getString("type of object")
                val dimension_details = menuItemObject.getString("dimension details")
                val material = menuItemObject.getString("material")
                val photo = menuItemObject.getString("photo")
                val Pieces_model = Pieces_model(name,description,date,culture,period,place_of_origin,type_of_object,dimension_details,material,photo)
                mRecyclerViewItems.add(Pieces_model)
            }
        }
        catch (exception: JSONException) {
            Log.e(Hotels::class.java.name, "Unable to parse JSON/JSON file.", exception)
            Toast.makeText(this,"Error : Server not working Successfully!", Toast.LENGTH_LONG).show()
        }
    }
    //English Language crocodile_museum_artifacts
    private fun addMenuItemsFromJson_Crocodile_Museum() {
        try {
            val jObject = JSONObject(readJsonDataFromFile())
            val menuItemsJsonArray = jObject.getJSONArray("crocodile_museum_artifacts")
            for (i in 0 until  menuItemsJsonArray.length()) {
                val menuItemObject = menuItemsJsonArray.getJSONObject(i)
                val name = menuItemObject.getString("name")
                val description = menuItemObject.getString("description")
                val date = menuItemObject.getString("date")
                val culture = menuItemObject.getString("culture")
                val period = menuItemObject.getString("name")
                val place_of_origin = menuItemObject.getString("place of origin")
                val type_of_object = menuItemObject.getString("type of object")
                val dimension_details = menuItemObject.getString("dimension details")
                val material = menuItemObject.getString("material")
                val photo = menuItemObject.getString("photo")
                val Pieces_model = Pieces_model(name,description,date,culture,period,place_of_origin,type_of_object,dimension_details,material,photo)
                mRecyclerViewItems.add(Pieces_model)
            }
        }
        catch (exception: JSONException) {
            Log.e(Hotels::class.java.name, "Unable to parse JSON/JSON file.", exception)
            Toast.makeText(this,"Error : Server not working Successfully!", Toast.LENGTH_LONG).show()
        }
    }
    //English Language nubia_museum_artifacts
    private fun addMenuItemsFromJson_Nubia_Museum() {
        try {
            val jObject = JSONObject(readJsonDataFromFile())
            val menuItemsJsonArray = jObject.getJSONArray("nubia_museum_artifacts")
            for (i in 0 until  menuItemsJsonArray.length()) {
                val menuItemObject = menuItemsJsonArray.getJSONObject(i)
                val name = menuItemObject.getString("name")
                val description = menuItemObject.getString("description")
                val date = menuItemObject.getString("date")
                val culture = menuItemObject.getString("culture")
                val period = menuItemObject.getString("name")
                val place_of_origin = menuItemObject.getString("place of origin")
                val type_of_object = menuItemObject.getString("type of object")
                val dimension_details = menuItemObject.getString("dimension details")
                val material = menuItemObject.getString("material")
                val photo = menuItemObject.getString("photo")
                val Pieces_model = Pieces_model(name,description,date,culture,period,place_of_origin,type_of_object,dimension_details,material,photo)
                mRecyclerViewItems.add(Pieces_model)
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
            val  inputStream: InputStream = assets.open("all_artifacts_pieces.json")
            json = inputStream.bufferedReader().use{it.readText()}
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        return json!!
    }
    //Arbic Language egyptian_museum_artifacts
    private fun addMenuItemsFromJson_ar_The_Egyptian_Museum() {
        try {
            val jObject = JSONObject(readJsonDataFromFile_ar())
            val menuItemsJsonArray = jObject.getJSONArray("egyptian_museum_artifacts")
            for (i in 0 until  menuItemsJsonArray.length()) {
                val menuItemObject = menuItemsJsonArray.getJSONObject(i)
                val name = menuItemObject.getString("name")
                val description = menuItemObject.getString("description")
                val date = menuItemObject.getString("date")
                val culture = menuItemObject.getString("culture")
                val period = menuItemObject.getString("name")
                val place_of_origin = menuItemObject.getString("place of origin")
                val type_of_object = menuItemObject.getString("type of object")
                val dimension_details = menuItemObject.getString("dimension details")
                val material = menuItemObject.getString("material")
                val photo = menuItemObject.getString("photo")
                val Pieces_model = Pieces_model(name,description,date,culture,period,place_of_origin,type_of_object,dimension_details,material,photo)
                mRecyclerViewItems.add(Pieces_model)
            }
        }
        catch (exception: JSONException) {
            Log.e(Hotels::class.java.name, "Unable to parse JSON/JSON file.", exception)
            Toast.makeText(this,"Error : Server not working Successfully!", Toast.LENGTH_LONG).show()
        }
    }
    @Throws(IOException::class)
    private fun readJsonDataFromFile_ar(): String {
        var json: String? = null
        try {
            val  inputStream: InputStream = assets.open("all_artifacts_pieces_ar.json")
            json = inputStream.bufferedReader().use{it.readText()}
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        return json!!
    }
    //French Language egyptian_museum_artifacts
    private fun addMenuItemsFromJson_fr_The_Egyptian_Museum() {
        try {
            val jObject = JSONObject(readJsonDataFromFile_fr())
            val menuItemsJsonArray = jObject.getJSONArray("egyptian_museum_artifacts")
            for (i in 0 until  menuItemsJsonArray.length()) {
                val menuItemObject = menuItemsJsonArray.getJSONObject(i)
                val name = menuItemObject.getString("name")
                val description = menuItemObject.getString("description")
                val date = menuItemObject.getString("date")
                val culture = menuItemObject.getString("culture")
                val period = menuItemObject.getString("name")
                val place_of_origin = menuItemObject.getString("place of origin")
                val type_of_object = menuItemObject.getString("type of object")
                val dimension_details = menuItemObject.getString("dimension details")
                val material = menuItemObject.getString("material")
                val photo = menuItemObject.getString("photo")
                val Pieces_model = Pieces_model(name,description,date,culture,period,place_of_origin,type_of_object,dimension_details,material,photo)
                mRecyclerViewItems.add(Pieces_model)
            }
        }
        catch (exception: JSONException) {
            Log.e(Hotels::class.java.name, "Unable to parse JSON/JSON file.", exception)
            Toast.makeText(this,"Error : Server not working Successfully!", Toast.LENGTH_LONG).show()
        }
    }
    @Throws(IOException::class)
    private fun readJsonDataFromFile_fr(): String {
        var json: String? = null
        try {
            val  inputStream: InputStream = assets.open("all_artifacts_pieces_fr.json")
            json = inputStream.bufferedReader().use{it.readText()}
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        return json!!
    }
}
