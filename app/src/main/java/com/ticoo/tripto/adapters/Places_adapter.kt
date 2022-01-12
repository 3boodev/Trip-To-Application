package com.ticoo.tripto.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.ticoo.tripto.R
import com.ticoo.tripto.models.Places_model


class Places_adapter(// An Activity's Context.
    private val mcontext: Context,
    private var mRecyclerViewItems: List<Any>


) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    inner class MenuItemViewHolder internal constructor(view: View) :
        RecyclerView.ViewHolder(view) {
        val places_Name: TextView = view.findViewById(R.id.places_name) as TextView
        val places_adress: TextView = view.findViewById(R.id.places_Address) as TextView
        val places_decription:TextView = view.findViewById(R.id.places_description)as TextView
        val places_Image: ImageView = view.findViewById(R.id.places_photo) as ImageView
        val places_map_location:TextView = view.findViewById(R.id.places_map_loc)as TextView
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
                    .inflate(R.layout.places_view, viewGroup, false)
                return MenuItemViewHolder(menuItemLayoutView)
            }
            else -> {
                val menuItemLayoutView = LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.places_view, viewGroup, false)
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
                val menuItem = mRecyclerViewItems[position] as Places_model

                menuItemHolder.places_Name.text=menuItem.name
                menuItemHolder.places_adress.text=menuItem.location
                menuItemHolder.places_decription.text=menuItem.decription
                Picasso.get().load(menuItem.photo).fit().centerInside().into(holder.places_Image)

                val tf : Typeface = Typeface.createFromAsset(mcontext.assets,"Cairo_Regular.ttf")
                menuItemHolder.places_Name.typeface = tf
                menuItemHolder.places_adress.typeface = tf
                menuItemHolder.places_decription.typeface = tf
                menuItemHolder.places_map_location.typeface=tf

                menuItemHolder.places_map_location.setOnClickListener {
                    val uri = "http://maps.google.com/maps?q=loc:${menuItem.lng}, ${menuItem.lat}"
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
                    intent.setPackage("com.google.android.apps.maps")
                    mcontext.startActivity(intent)
                }
                // Add the menu item details to the menu item view.
            }
            else -> {
            }
        }
    }
    fun filterList(filteredList: ArrayList<Places_model>) {
        mRecyclerViewItems = filteredList
        notifyDataSetChanged()
    }
    companion object {
        private val MENU_ITEM_VIEW_TYPE = 0
        private val NATIVE_EXPRESS_AD_VIEW_TYPE = 1
    }
}