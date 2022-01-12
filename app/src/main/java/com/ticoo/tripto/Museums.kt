package com.ticoo.tripto

import android.app.Dialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Window
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ticoo.tripto.adapters.Museums_adapter
import com.ticoo.tripto.models.Museums_model
import kotlinx.android.synthetic.main.activity_museums.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream
import java.lang.Exception

class Museums : AppCompatActivity() {
    private val mRecyclerViewItems = ArrayList<Museums_model>()
    val adapter = Museums_adapter(this, mRecyclerViewItems)
    private val PICK_IMAGE_REQUEST = 71
    private val CAMERA = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_museums)
        btn_camera.setOnClickListener {showPictureDialog()}
        val mRecyclerView = findViewById<RecyclerView>(R.id.musim_recycler)
        mRecyclerView.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        mRecyclerView.layoutManager = layoutManager
        mRecyclerView.adapter = adapter

        val myPrefs=applicationContext.getSharedPreferences("Setting", Context.MODE_PRIVATE)
        val lang=myPrefs.getString("lang","")
        val city=myPrefs.getString("city","")

        if(lang=="English"&&city=="Cairo"){addMenuItemsFromJson()}
        else if (lang=="English"&&city=="Alexandria"){addMenuItemsFromalexJson()}
        else if(lang=="English"&&city=="Aswan"){addMenuItemsFromaswanJson()}
        else if (lang=="English"&&city=="Luxor"){addMenuItemsFromluxorJson()}

        else if(lang=="Arabic"&&city=="Cairo"){addMenuItemsFromarJson()}
        else if (lang=="Arabic"&&city=="Alexandria"){addMenuItemsFromalexarJson()}
        else if(lang=="Arabic"&&city=="Aswan"){addMenuItemsFromaswanarJson()}
        else if (lang=="Arabic"&&city=="Luxor"){addMenuItemsFromluxorarJson()}

        else if(lang=="French"&&city=="Cairo"){addMenuItemsFromfrJson()}
        else if (lang=="French"&&city=="Alexandria"){addMenuItemsFromalexfrJson()}
        else if(lang=="French"&&city=="Aswan"){addMenuItemsFromaswanfrJson()}
        else if (lang=="French"&&city=="Luxor"){addMenuItemsFromluxorfrJson()}

        else{Toast.makeText(this,"Error in Selected City or Language ",Toast.LENGTH_SHORT).show()}


        musim_search.addTextChangedListener(object : TextWatcher {
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
        val filteredList = ArrayList<Museums_model>()

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
            val menuItemsJsonArray = jObject.getJSONArray("cairo_museums")
            for (i in 0 until  menuItemsJsonArray.length()) {
                val menuItemObject = menuItemsJsonArray.getJSONObject(i)
                val name = menuItemObject.getString("name")
                val photo = menuItemObject.getString("photo")
                val Museums_model = Museums_model(name,photo)
                mRecyclerViewItems.add(Museums_model)
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
            val  inputStream: InputStream = assets.open("all_museums.json")
            json = inputStream.bufferedReader().use{it.readText()}
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        return json!!
    }
    //English Language alex
    private fun addMenuItemsFromalexJson() {
        try {
            val jObject = JSONObject(readJsonDataFromalexFile())
            val menuItemsJsonArray = jObject.getJSONArray("alexandria_museums")
            for (i in 0 until  menuItemsJsonArray.length()) {
                val menuItemObject = menuItemsJsonArray.getJSONObject(i)
                val name = menuItemObject.getString("name")
                val photo = menuItemObject.getString("photo")
                val Museums_model = Museums_model(name,photo)
                mRecyclerViewItems.add(Museums_model)
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
            val  inputStream: InputStream = assets.open("all_museums.json")
            json = inputStream.bufferedReader().use{it.readText()}
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        return json!!
    }
    //English Language aswan
    private fun addMenuItemsFromaswanJson() {
        try {
            val jObject = JSONObject(readJsonDataFromaswanFile())
            val menuItemsJsonArray = jObject.getJSONArray("aswan_museums")
            for (i in 0 until  menuItemsJsonArray.length()) {
                val menuItemObject = menuItemsJsonArray.getJSONObject(i)
                val name = menuItemObject.getString("name")
                val photo = menuItemObject.getString("photo")
                val Museums_model = Museums_model(name,photo)
                mRecyclerViewItems.add(Museums_model)
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
            val  inputStream: InputStream = assets.open("all_museums.json")
            json = inputStream.bufferedReader().use{it.readText()}
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        return json!!
    }
    //English Language luxor
    private fun addMenuItemsFromluxorJson() {
        try {
            val jObject = JSONObject(readJsonDataFromluxorFile())
            val menuItemsJsonArray = jObject.getJSONArray("luxor_museums")
            for (i in 0 until  menuItemsJsonArray.length()) {
                val menuItemObject = menuItemsJsonArray.getJSONObject(i)
                val name = menuItemObject.getString("name")
                val photo = menuItemObject.getString("photo")
                val Museums_model = Museums_model(name,photo)
                mRecyclerViewItems.add(Museums_model)
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
            val  inputStream: InputStream = assets.open("all_museums.json")
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
            val menuItemsJsonArray = jObject.getJSONArray("cairo_museums")
            for (i in 0 until  menuItemsJsonArray.length()) {
                val menuItemObject = menuItemsJsonArray.getJSONObject(i)
                val name = menuItemObject.getString("name")
                val photo = menuItemObject.getString("photo")
                val Museums_model = Museums_model(name,photo)
                mRecyclerViewItems.add(Museums_model)
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
            val  inputStream: InputStream = assets.open("all_museums_ar.json")
            json = inputStream.bufferedReader().use{it.readText()}
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        return json!!
    }
    //Arabic Language alex
    private fun addMenuItemsFromalexarJson() {
        try {
            val jObject = JSONObject(readJsonDataFromalexarFile())
            val menuItemsJsonArray = jObject.getJSONArray("alexandria_museums")
            for (i in 0 until  menuItemsJsonArray.length()) {
                val menuItemObject = menuItemsJsonArray.getJSONObject(i)
                val name = menuItemObject.getString("name")
                val photo = menuItemObject.getString("photo")
                val Museums_model = Museums_model(name,photo)
                mRecyclerViewItems.add(Museums_model)
            }
        }
        catch (exception: JSONException) {
            Log.e(Hotels::class.java.name, "Unable to parse JSON/JSON file.", exception)
            Toast.makeText(this,"Error : Server not working Successfully!", Toast.LENGTH_LONG).show()
        }
    }

    @Throws(IOException::class)
    private fun readJsonDataFromalexarFile(): String {
        var json: String? = null
        try {
            val  inputStream: InputStream = assets.open("all_museums_ar.json")
            json = inputStream.bufferedReader().use{it.readText()}
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        return json!!
    }
    //Arabic Language aswan
    private fun addMenuItemsFromaswanarJson() {
        try {
            val jObject = JSONObject(readJsonDataFromaswanarFile())
            val menuItemsJsonArray = jObject.getJSONArray("aswan_museums")
            for (i in 0 until  menuItemsJsonArray.length()) {
                val menuItemObject = menuItemsJsonArray.getJSONObject(i)
                val name = menuItemObject.getString("name")
                val photo = menuItemObject.getString("photo")
                val Museums_model = Museums_model(name,photo)
                mRecyclerViewItems.add(Museums_model)
            }
        }
        catch (exception: JSONException) {
            Log.e(Hotels::class.java.name, "Unable to parse JSON/JSON file.", exception)
            Toast.makeText(this,"Error : Server not working Successfully!", Toast.LENGTH_LONG).show()
        }
    }

    @Throws(IOException::class)
    private fun readJsonDataFromaswanarFile(): String {
        var json: String? = null
        try {
            val  inputStream: InputStream = assets.open("all_museums_ar.json")
            json = inputStream.bufferedReader().use{it.readText()}
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        return json!!
    }
    //Arabic Language luxor
    private fun addMenuItemsFromluxorarJson() {
        try {
            val jObject = JSONObject(readJsonDataFromluxorarFile())
            val menuItemsJsonArray = jObject.getJSONArray("luxor_museums")
            for (i in 0 until  menuItemsJsonArray.length()) {
                val menuItemObject = menuItemsJsonArray.getJSONObject(i)
                val name = menuItemObject.getString("name")
                val photo = menuItemObject.getString("photo")
                val Museums_model = Museums_model(name,photo)
                mRecyclerViewItems.add(Museums_model)
            }
        }
        catch (exception: JSONException) {
            Log.e(Hotels::class.java.name, "Unable to parse JSON/JSON file.", exception)
            Toast.makeText(this,"Error : Server not working Successfully!", Toast.LENGTH_LONG).show()
        }
    }

    @Throws(IOException::class)
    private fun readJsonDataFromluxorarFile(): String {
        var json: String? = null
        try {
            val  inputStream: InputStream = assets.open("all_museums_ar.json")
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
            val menuItemsJsonArray = jObject.getJSONArray("cairo_museums")
            for (i in 0 until  menuItemsJsonArray.length()) {
                val menuItemObject = menuItemsJsonArray.getJSONObject(i)
                val name = menuItemObject.getString("name")
                val photo = menuItemObject.getString("photo")
                val Museums_model = Museums_model(name,photo)
                mRecyclerViewItems.add(Museums_model)
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
            val  inputStream: InputStream = assets.open("all_museums_fr.json")
            json = inputStream.bufferedReader().use{it.readText()}
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        return json!!
    }
    //French Language alex
    private fun addMenuItemsFromalexfrJson() {
        try {
            val jObject = JSONObject(readJsonDataFromalexfrFile())
            val menuItemsJsonArray = jObject.getJSONArray("alexandria_museums")
            for (i in 0 until  menuItemsJsonArray.length()) {
                val menuItemObject = menuItemsJsonArray.getJSONObject(i)
                val name = menuItemObject.getString("name")
                val photo = menuItemObject.getString("photo")
                val Museums_model = Museums_model(name,photo)
                mRecyclerViewItems.add(Museums_model)
            }
        }
        catch (exception: JSONException) {
            Log.e(Hotels::class.java.name, "Unable to parse JSON/JSON file.", exception)
            Toast.makeText(this,"Error : Server not working Successfully!", Toast.LENGTH_LONG).show()
        }
    }

    @Throws(IOException::class)
    private fun readJsonDataFromalexfrFile(): String {
        var json: String? = null
        try {
            val  inputStream: InputStream = assets.open("all_museums_fr.json")
            json = inputStream.bufferedReader().use{it.readText()}
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        return json!!
    }
    //French Language aswan
    private fun addMenuItemsFromaswanfrJson() {
        try {
            val jObject = JSONObject(readJsonDataFromaswanfrFile())
            val menuItemsJsonArray = jObject.getJSONArray("aswan_museums")
            for (i in 0 until  menuItemsJsonArray.length()) {
                val menuItemObject = menuItemsJsonArray.getJSONObject(i)
                val name = menuItemObject.getString("name")
                val photo = menuItemObject.getString("photo")
                val Museums_model = Museums_model(name,photo)
                mRecyclerViewItems.add(Museums_model)
            }
        }
        catch (exception: JSONException) {
            Log.e(Hotels::class.java.name, "Unable to parse JSON/JSON file.", exception)
            Toast.makeText(this,"Error : Server not working Successfully!", Toast.LENGTH_LONG).show()
        }
    }

    @Throws(IOException::class)
    private fun readJsonDataFromaswanfrFile(): String {
        var json: String? = null
        try {
            val  inputStream: InputStream = assets.open("all_museums_fr.json")
            json = inputStream.bufferedReader().use{it.readText()}
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        return json!!
    }
    //French Language luxor
    private fun addMenuItemsFromluxorfrJson() {
        try {
            val jObject = JSONObject(readJsonDataFromluxorfrFile())
            val menuItemsJsonArray = jObject.getJSONArray("luxor_museums")
            for (i in 0 until  menuItemsJsonArray.length()) {
                val menuItemObject = menuItemsJsonArray.getJSONObject(i)
                val name = menuItemObject.getString("name")
                val photo = menuItemObject.getString("photo")
                val Museums_model = Museums_model(name,photo)
                mRecyclerViewItems.add(Museums_model)
            }
        }
        catch (exception: JSONException) {
            Log.e(Hotels::class.java.name, "Unable to parse JSON/JSON file.", exception)
            Toast.makeText(this,"Error : Server not working Successfully!", Toast.LENGTH_LONG).show()
        }
    }

    @Throws(IOException::class)
    private fun readJsonDataFromluxorfrFile(): String {
        var json: String? = null
        try {
            val  inputStream: InputStream = assets.open("all_museums_fr.json")
            json = inputStream.bufferedReader().use{it.readText()}
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        return json!!
    }
    private fun showPictureDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.camera_dialog)
        val camera = dialog.findViewById(R.id.dialog_camera) as ImageView
        val galary = dialog.findViewById(R.id.dialog_galary) as ImageView
        camera.setOnClickListener {
            takePhotoFromCamera()
            dialog.hide()
            startActivity(Intent(this,Details::class.java))
        }
        galary.setOnClickListener {
            choosePhotoFromGallary()
            dialog.hide()
            startActivity(Intent(this,Details::class.java))
        }
        dialog.show()
    }

    fun choosePhotoFromGallary() {
        try {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "اختر الصوره"), PICK_IMAGE_REQUEST)
        }catch (e: Exception){
            Toast.makeText(this, "اعطي السماحيه لأستخدام معرض الصور", Toast.LENGTH_LONG).show()}
    }

    private fun takePhotoFromCamera() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(takePictureIntent, CAMERA)
    }
}
