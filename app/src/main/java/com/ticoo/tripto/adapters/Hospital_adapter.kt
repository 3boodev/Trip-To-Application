package com.ticoo.tripto.adapters

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Typeface
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.ticoo.tripto.R
import com.ticoo.tripto.models.Hospital_model


class Hospital_adapter(// An Activity's Context.
    private val mcontext: Context,
    private var mRecyclerViewItems: List<Any>


) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    inner class MenuItemViewHolder internal constructor(view: View) :
        RecyclerView.ViewHolder(view) {
        val hospital_Name: TextView = view.findViewById(R.id.hospital_name) as TextView
        val hospital_adress: TextView = view.findViewById(R.id.hospital_Address) as TextView
        val hospital_phone: TextView = view.findViewById(R.id.hospital_phone) as TextView
        val hospital_Image: ImageView = view.findViewById(R.id.hospital_photo) as ImageView
        val hospital_map_location:TextView = view.findViewById(R.id.hospital_map_loc)as TextView
    }

    class NativeExpressAdViewHolder internal constructor(view: View) :
        RecyclerView.ViewHolder(view)

    override fun getItemCount(): Int {
        return mRecyclerViewItems.size
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            MENU_ITEM_VIEW_TYPE -> {
                val menuItemLayoutView = LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.hospital_view, viewGroup, false)
                return MenuItemViewHolder(menuItemLayoutView)
            }
            else -> {
                val menuItemLayoutView = LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.hospital_view, viewGroup, false)
                return MenuItemViewHolder(menuItemLayoutView)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewType = getItemViewType(position)
        when (viewType) {
            MENU_ITEM_VIEW_TYPE -> {
                val menuItemHolder = holder as MenuItemViewHolder
                val menuItem = mRecyclerViewItems[position] as Hospital_model

                menuItemHolder.hospital_Name.text=menuItem.name
                menuItemHolder.hospital_adress.text=menuItem.location
                menuItemHolder.hospital_phone.text=menuItem.telephone
                Picasso.get().load(menuItem.photo).fit().centerInside().into(holder.hospital_Image)
                val tf : Typeface = Typeface.createFromAsset(mcontext.assets,"Cairo_Regular.ttf")
                menuItemHolder.hospital_Name.typeface = tf
                menuItemHolder.hospital_adress.typeface = tf
                menuItemHolder.hospital_phone.typeface = tf
                menuItemHolder.hospital_map_location.typeface=tf
                menuItemHolder.hospital_map_location.setOnClickListener {
                    val uri = "http://maps.google.com/maps?q=loc:${menuItem.lng}, ${menuItem.lat}"
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
                    intent.setPackage("com.google.android.apps.maps")
                    mcontext.startActivity(intent)
                }
                menuItemHolder.hospital_phone.setOnClickListener {
                    val callIntent = Intent(Intent.ACTION_CALL)
                    callIntent.data = Uri.parse("tel:${menuItem.telephone}")
                    if (ActivityCompat.checkSelfPermission(mcontext, Manifest.permission.CALL_PHONE) !== PackageManager.PERMISSION_GRANTED)
                    { return@setOnClickListener}
                    mcontext.startActivity(callIntent)
                }
                // Add the menu item details to the menu item view.
            }
            else -> {
            }
        }
    }
    fun filterList(filteredList: ArrayList<Hospital_model>) {
        mRecyclerViewItems = filteredList
        notifyDataSetChanged()
    }
    companion object {
        private val MENU_ITEM_VIEW_TYPE = 0
        private val NATIVE_EXPRESS_AD_VIEW_TYPE = 1
    }
}