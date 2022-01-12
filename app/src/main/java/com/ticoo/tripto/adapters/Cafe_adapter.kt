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
import com.ticoo.tripto.models.Cafe_model

class Cafe_adapter(// An Activity's Context.
    private val mcontext: Context,
    private var mRecyclerViewItems: List<Any>


) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    inner class MenuItemViewHolder internal constructor(view: View) :
        RecyclerView.ViewHolder(view) {
        val cafe_Name: TextView = view.findViewById(R.id.cafe_name) as TextView
        val cafe_adress: TextView = view.findViewById(R.id.cafe_Address) as TextView
        val cafe_phone: TextView = view.findViewById(R.id.cafe_phone) as TextView
        val cafe_decription:TextView = view.findViewById(R.id.cafe_description)as TextView
        val cafe_website:TextView = view.findViewById(R.id.cafe_website)as TextView
        val cafe_Image: ImageView = view.findViewById(R.id.cafe_photo) as ImageView
        val cafe_map_location:TextView = view.findViewById(R.id.cafe_map_loc)as TextView
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
                    .inflate(R.layout.cafe_view, viewGroup, false)
                return MenuItemViewHolder(menuItemLayoutView)
            }
            else -> {
                val menuItemLayoutView = LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.cafe_view, viewGroup, false)
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
                val menuItem = mRecyclerViewItems[position] as Cafe_model

                menuItemHolder.cafe_Name.text=menuItem.name
                menuItemHolder.cafe_adress.text=menuItem.location
                menuItemHolder.cafe_decription.text=menuItem.decription
                menuItemHolder.cafe_phone.text=menuItem.telephone
               // menuItemHolder.cafe_website.text=menuItem.website
                Picasso.get().load(menuItem.photo).fit().centerInside().into(holder.cafe_Image)
                val tf : Typeface = Typeface.createFromAsset(mcontext.assets,"Cairo_Regular.ttf")
                menuItemHolder.cafe_Name.typeface = tf
                menuItemHolder.cafe_adress.typeface = tf
                menuItemHolder.cafe_phone.typeface = tf
                menuItemHolder.cafe_decription.typeface=tf
                menuItemHolder.cafe_website.typeface=tf
                menuItemHolder.cafe_map_location.typeface=tf
                menuItemHolder.cafe_map_location.setOnClickListener {
                    val uri = "http://maps.google.com/maps?q=loc:${menuItem.lng}, ${menuItem.lat}"
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
                    intent.setPackage("com.google.android.apps.maps")
                    mcontext.startActivity(intent)
                }
                menuItemHolder.cafe_website.setOnClickListener {
                    val openURL = Intent(Intent.ACTION_VIEW)
                    openURL.data = Uri.parse(menuItem.website)
                    mcontext.startActivity(openURL)
                }
                menuItemHolder.cafe_phone.setOnClickListener {
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
    fun filterList(filteredList: ArrayList<Cafe_model>) {
        mRecyclerViewItems = filteredList
        notifyDataSetChanged()
    }
    companion object {
        private val MENU_ITEM_VIEW_TYPE = 0
    }
}