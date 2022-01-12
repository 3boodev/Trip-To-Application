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
import com.ticoo.tripto.models.Hotels_model


class Hotel_adapter(// An Activity's Context.
    private val mcontext: Context,
    private var mRecyclerViewItems: List<Any>


) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
     inner class MenuItemViewHolder internal constructor(view: View) :
        RecyclerView.ViewHolder(view) {
         val hotel_Name: TextView = view.findViewById(R.id.hotel_name) as TextView
         val hotel_adress: TextView = view.findViewById(R.id.hotel_Address) as TextView
         val hotel_phone: TextView = view.findViewById(R.id.hotel_phone) as TextView
         val hotel_decription:TextView = view.findViewById(R.id.hotel_description)as TextView
         val hotel_website:TextView = view.findViewById(R.id.hotel_website)as TextView
         val hotel_Image: ImageView = view.findViewById(R.id.hotel_photo) as ImageView
         val hotel_map_location:TextView = view.findViewById(R.id.hotel_map_loc)as TextView
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
                    .inflate(R.layout.hotel_view, viewGroup, false)
                return MenuItemViewHolder(menuItemLayoutView)
            }
            else -> {
                val menuItemLayoutView = LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.hotel_view, viewGroup, false)
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
                val menuItem = mRecyclerViewItems[position] as Hotels_model

                menuItemHolder.hotel_Name.text=menuItem.name
                menuItemHolder.hotel_adress.text=menuItem.location
                menuItemHolder.hotel_decription.text=menuItem.decription
                menuItemHolder.hotel_phone.text=menuItem.telephone
                Picasso.get().load(menuItem.photo).fit().centerInside().into(holder.hotel_Image)
                val tf : Typeface = Typeface.createFromAsset(mcontext.assets,"Cairo_Regular.ttf")
                menuItemHolder.hotel_Name.typeface = tf
                menuItemHolder.hotel_adress.typeface = tf
                menuItemHolder.hotel_phone.typeface = tf
                menuItemHolder.hotel_decription.typeface=tf
                menuItemHolder.hotel_website.typeface = tf
                menuItemHolder.hotel_map_location.typeface=tf
                menuItemHolder.hotel_map_location.setOnClickListener {
                    val uri = "http://maps.google.com/maps?q=loc:${menuItem.lng}, ${menuItem.lat}"
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
                    intent.setPackage("com.google.android.apps.maps")
                    mcontext.startActivity(intent)
                }
                menuItemHolder.hotel_website.setOnClickListener {
                    val openURL = Intent(Intent.ACTION_VIEW)
                    openURL.data = Uri.parse(menuItem.website)
                    mcontext.startActivity(openURL)
                }
                menuItemHolder.hotel_phone.setOnClickListener {
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
    fun filterList(filteredList: ArrayList<Hotels_model>) {
        mRecyclerViewItems = filteredList
        notifyDataSetChanged()
    }
    companion object {
        private val MENU_ITEM_VIEW_TYPE = 0
        private val NATIVE_EXPRESS_AD_VIEW_TYPE = 1
    }
}